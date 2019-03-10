package org.vision.frames;

import lombok.extern.slf4j.Slf4j;
import org.vision.netty.VisionNettyServer;
import org.vision.utils.Addressing;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;

@Slf4j
public class MainFrame extends JFrame {

    private NewClientDialog dialog = new NewClientDialog();
    private InetAddress inetAddress;
    private Thread thread;
    private VisionNettyServer server;

    public MainFrame(String name, List<VisionClient> visionClientList, InetAddress inetAddress) throws SocketException {

        super(name);
        // 启动Netty Server监听
        this.inetAddress = inetAddress;
        Runnable runnable = () -> {
            server = new VisionNettyServer(Addressing.inetHostFormat(this.inetAddress));
            try {
                server.bind(2323);
            } catch (Exception e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        };

        thread = new Thread(runnable);
        thread.setUncaughtExceptionHandler((t, e12) -> {
            log.error(e12.getMessage());
            t.interrupt();
        });

        // 开始监听
        thread.start();
        System.out.println("启动监听线程成功！");

        //窗体配置
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        //左边Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setMinimumSize(new Dimension(300, 100));
        leftPanel.setBorder(BorderFactory.createEtchedBorder());

        //网络地址
        JPanel leftTopPanel = new JPanel();
        leftTopPanel.setLayout(new FlowLayout());
        leftTopPanel.setMinimumSize(new Dimension(300, 100));

        JLabel addressLabel = new JLabel(Addressing.inetHostFormat(inetAddress));
        addressLabel.setMinimumSize(new Dimension(150,30));
        leftTopPanel.add(addressLabel);

        //左边学员表格
        AbstractTableModel clientModel = new ClientModel(visionClientList);
        JTable clientTable = new JTable(clientModel);
        ListSelectionModel selectionModel = clientTable.getSelectionModel();

        leftPanel.add(leftTopPanel, BorderLayout.NORTH);
        leftPanel.add(new JScrollPane(clientTable), BorderLayout.CENTER);

        //创建按钮
        JPanel leftBottomPanel = new JPanel();
        leftBottomPanel.setLayout(new FlowLayout());
        leftBottomPanel.setPreferredSize(new Dimension(300, 50));
        JButton btnCreate = new JButton("创建");
        btnCreate.setSize(new Dimension(90, 30));
        btnCreate.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(true);
                if (null != dialog.getVisionClient()) {
                    if (!visionClientList.contains(dialog.getVisionClient())) {
                        visionClientList.add(dialog.getVisionClient());
                        clientModel.fireTableDataChanged();
                        clientTable.setRowSelectionInterval(visionClientList.size() - 1, visionClientList.size() - 1);
                        server.setVisionClient(visionClientList.get(clientTable.getSelectedRow()));
                    }
                }
            }
        });
        leftBottomPanel.add(btnCreate);
        leftPanel.add(leftBottomPanel, BorderLayout.SOUTH);

        //右边眼睛筛查记录表格
        AbstractTableModel recordModel = new RecordModel(null);
        JTable rightTable = new JTable(recordModel);

        //选择刷新
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        selectionModel.addListSelectionListener(e -> {
            if (-1 != clientTable.getSelectedRow()) {
                ((RecordModel) recordModel).setVisionClientRecordList(visionClientList.get(clientTable.getSelectedRow()).getRecordList());
                recordModel.fireTableDataChanged();
            }
        });

        //右边Panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(500, 600));
        rightPanel.setBorder(BorderFactory.createEtchedBorder());
        rightPanel.add(new JScrollPane(rightTable), BorderLayout.CENTER);

        //把左右Panel加入根容器
        Container contentPane = getContentPane();
        contentPane.add(leftPanel, BorderLayout.WEST);
        contentPane.add(rightPanel, BorderLayout.CENTER);
    }


}
