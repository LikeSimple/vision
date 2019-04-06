package org.vision.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.Setter;
import org.vision.frames.VisionClient;
import org.vision.frames.VisionClientRecord;
import org.vision.utils.CRC16Util;

import java.io.*;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static org.vision.utils.ByteArrayUtil.combineArray;
import static org.vision.utils.ByteArrayUtil.reverseArray;

public class VisionNettyServer {

    private final static byte[] INSTRUCTION_READY = new byte[]{0x01};
    private final static byte[] INSTRUCTION_BEGIN = new byte[]{(byte) 0xEE};
    private final static byte[] INSTRUCTION_END = new byte[]{(byte) 0xEE};
    private final static byte[] INSTRUCTION_REPEAT = new byte[]{(byte) 0xFF};

    @Setter
    private VisionClient visionClient = new VisionClient();
    private String inetHost;

    public VisionNettyServer(String inetHost) {
        this.inetHost = inetHost;
    }

    public void bind(int port) throws Exception {
        //配置服务端的NIO线程组
        //实际上EventLoopGroup就是Reactor线程组
        //两个Reactor一个用于服务端接收客户端的连接，另一个用于进行SocketChannel的网络读写
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            /**
             * 由于我们使用在 NIO 传输，我们
             已指定 NioEventLoopGroup接受和处理新连接，指定 NioServerSocketChannel
             为信道类型。在此之后，我们设置本地地址是 InetSocketAddress 与所选择的端口（6）如。
             服务器将绑定到此地址来监听新的连接请求。
             */
            //ServerBootstrap对象是Netty用于启动NIO服务端的辅助启动类，目的是降低服务端开发的复杂度
            ServerBootstrap b = new ServerBootstrap();
            //Set the EventLoopGroup for the parent (acceptor) and the child (client).
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .localAddress(new InetSocketAddress(port))
                    //绑定I/O事件的处理类ChildChannelHandler,作用类似于Reactor模式中的Handler类
                    //主要用于处理网络I/O事件，例如记录日志，对消息进行编解码等
                    .childHandler(new ChildServerHandler());
            //绑定监听端口，调用sync同步阻塞方法等待绑定操作完成，完成后返回ChannelFuture类似于JDK中Future
            ChannelFuture f = b.bind(inetHost, port).sync();
            //使用sync方法进行阻塞，等待服务端链路关闭之后Main函数才退出
            f.channel().closeFuture().sync();
            System.out.println("Netty 监听关闭");
        } finally {
            //优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildServerHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            System.out.println(String.format("接受到客户端[%s]的请求通讯要求", socketChannel.localAddress().getAddress() + ":" + socketChannel.localAddress().getPort()));
            socketChannel.pipeline().addLast(new VisionReadyHandler());
            socketChannel.pipeline().addLast(new VisionDecoder(1 << 20, 36, 3));
            socketChannel.pipeline().addLast(new VisionServerHandler());
        }
    }

    private class VisionReadyHandler extends ChannelHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            System.out.println("接收到数据：" + buf.readableBytes());
            if (buf.readableBytes() == 1) {
                byte magic = buf.readByte();
                if (magic == 0x01) {
                    System.out.println("收到握手确认指令，发送请求数据指令，开始传输");
                    ByteBuf resp = Unpooled.copiedBuffer(INSTRUCTION_BEGIN);
                    ctx.write(resp);
                    ctx.flush();
                } else {
                    System.out.println("客户端返回错误指令，重新发送握手指令！");
                    ByteBuf response = Unpooled.copiedBuffer(INSTRUCTION_READY);
                    ctx.write(response);
                    ctx.flush();
                }
            } else {
                System.out.println("数据帧处理");
                ctx.fireChannelRead(msg);
            }
        }

        @Override
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            System.out.println("向客户端发送握手指令");
            ByteBuf response = Unpooled.copiedBuffer(INSTRUCTION_READY);
            ctx.write(response);
            ctx.flush();
        }
    }

    /**
     * 数据帧解析器
     */
    private class VisionDecoder extends LengthFieldBasedFrameDecoder {

        private final static int HEADER_SIZE = 39;

        public VisionDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
            super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        }

        @Override
        protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

            if (in == null) {
                return null;
            }

            System.out.println("接到数据" + in.readableBytes());

            if (in.readableBytes() <= HEADER_SIZE) {
                //头部数据不全，等待
                return null;
            }

            in.markReaderIndex();
            // 读取头部数据
            byte[] header = new byte[HEADER_SIZE];
            in.readBytes(header);

            VisionMessageHeader messageHeader = new VisionMessageHeader(header);

            //发送的包数据不全，继续等待
            if (in.readableBytes() < messageHeader.getLength() + 2) {
                // 此处非常重要，重置输入流的读指针
                in.resetReaderIndex();
                return null;
            }

            byte[] data = new byte[messageHeader.getLength()];
            in.readBytes(data);

            byte[] crc16 = new byte[2];
            in.readBytes(crc16);
            reverseArray(crc16);
            System.out.println("Origin CRC16: " + (new BigInteger(crc16).intValue()));

            byte[] allData = combineArray(header, data);
            System.out.println("CRC16: " + CRC16Util.calcCrc16(allData));

            if (new BigInteger(crc16).intValue() != CRC16Util.calcCrc16(allData)) {
                // CRC校验没通过，要求重传
                System.out.println("接收的数据CRC校验没通过，向客户端发送重发指令");
                ByteBuf response = Unpooled.copiedBuffer(INSTRUCTION_REPEAT);
                ctx.write(response);
                ctx.flush();
                return null;
            }
            System.out.println("接收的数据CRC校验通过");

            VisionMessageData messageData = new VisionMessageData(data, messageHeader.getDataType());
            return new VisionMessage(messageHeader, messageData, crc16);
        }
    }

    private class VisionServerHandler extends ChannelHandlerAdapter {

        private String basePath = "";

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            // 对Message进行业务处理
            VisionMessage message = (VisionMessage) msg;
            //处理业务(根目录，一个文本文件，图片文件，图片文件名，用户ID包含在本文文件内）
            if ("".equals(basePath)) {
                //创建今日主目录
                DateFormat df = new SimpleDateFormat("yyyyMMddHH");
                basePath = "vision/" + df.format(new Date()) + "/";
                Path base = FileSystems.getDefault().getPath(basePath);
                if (Files.notExists(base, NOFOLLOW_LINKS)) {
                    Files.createDirectories(base);
                }
            }
            saveMessage(basePath, message);
            //发送本次传送结束指令
            System.out.println("数据接收完成，向客户端发送结束指令");
            ByteBuf response = Unpooled.copiedBuffer(INSTRUCTION_END);
            ctx.write(response);
            ctx.flush();
        }

        private void saveMessage(String basePath, VisionMessage message) {

            //图形文件
            switch (message.getHeader().getDataType()) {
                case 2:
                    String imageFile = basePath + visionClient.getActivityId() + "_" + visionClient.getClientId() + "_"
                            + message.getHeader().getDate() + message.getHeader().getTime() + ".jpg";
                    BufferedOutputStream outputStream = null;

                    try {
                        Path imagePath = FileSystems.getDefault().getPath(imageFile);
                        if (Files.notExists(imagePath, NOFOLLOW_LINKS)) {
                            Files.createFile(imagePath);
                        }
                        outputStream = new BufferedOutputStream(new FileOutputStream(new File(imageFile)));
                        outputStream.write(message.getData().getData());
                        System.out.println("图像保存成功！");


                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        try {
                            if (outputStream != null) {
                                outputStream.flush();
                                outputStream.close();
                            }
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 1:
                    visionClient.getRecordList().add(new VisionClientRecord(message));
                    // CSV文件(Append)
                    String file = basePath + message.getHeader().getDate() + "_vision_check_data.csv";
                    PrintWriter writer = null;
                    try {
                        Path filePath = FileSystems.getDefault().getPath(file);
                        if (Files.notExists(filePath, NOFOLLOW_LINKS)) {
                            System.out.println("新建文件:" + file);
                            Files.createFile(filePath);
                            writer = new PrintWriter(new FileOutputStream(filePath.toFile()));
                            System.out.println("插入csv头部信息");
                            writer.append(message.toCSVHeader());
                        } else {
                            writer = new PrintWriter(new FileOutputStream(filePath.toFile(), true));
                        }
                        System.out.println("写入视力筛查数据！");
                        writer.append(message.toCSVData(visionClient.getClientId(), visionClient.getActivityId()));
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        if (writer != null) {
                            writer.flush();
                            writer.close();
                        }
                    }
                    break;
                default:
            }

        }

        @Override
        public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
            System.out.println("退出本次通信");
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }


}


