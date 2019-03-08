package main.java.org.vision.netty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static main.java.org.vision.utils.ByteArrayUtil.reverseArray;


@Data
@ToString
@NoArgsConstructor
public class VisionMessageHeader {

    private Integer magic;

    private String idNumber;

    private Integer deviceType;

    private Integer eyeType;

    private String date;

    private String time;

    private Integer dataType;

    private int length;

    public VisionMessageHeader(byte[] header) {

        magic = new Integer(header[0]);

        idNumber = new String(Arrays.copyOfRange(header, 1, 19), StandardCharsets.UTF_8);

        deviceType = new Integer(header[19]);

        eyeType = new Integer(header[20]);

        date = new String(Arrays.copyOfRange(header, 21, 29), StandardCharsets.UTF_8);

        time = new String(Arrays.copyOfRange(header, 29, 35), StandardCharsets.UTF_8);

        dataType = new Integer(header[35]);

        byte[] originLength = Arrays.copyOfRange(header, 36, 39);
        reverseArray(originLength);
        length = new BigInteger(originLength).intValue();
    }

    public String toCSV() {
        StringBuilder builder = new StringBuilder();
        return builder.append(magic).append(",")
                .append(idNumber).append(",")
                .append(deviceType).append(",")
                .append(eyeType).append(",")
                .append(date).append(",")
                .append(time).append(",")
                .append(dataType).append(",")
                .append(length).toString();
    }
}
