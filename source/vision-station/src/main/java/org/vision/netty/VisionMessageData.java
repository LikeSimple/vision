package org.vision.netty;

import lombok.Data;
import lombok.ToString;
import org.vision.utils.FieldIgnore;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Data
@ToString(exclude = {"data"})
@FieldIgnore(value = {"data"})
public class VisionMessageData {

    private byte[] data;

    private String pupil = null;

    private String se1 = null;

    private String ds1 = null;

    private String dc1 = null;

    private String axis1 = null;

    private String se2 = null;

    private String ds2 = null;

    private String dc2 = null;

    private String axis2 = null;

    private String pd = null;

    private String mmHg = null;

    private String gazeH = null;

    private String gazeV = null;

    public String toCSV() {
        StringBuilder builder = new StringBuilder();
        return builder.append(pupil).append(",")
                .append(se1).append(",")
                .append(ds1).append(",")
                .append(dc1).append(",")
                .append(axis1).append(",")
                .append(se2).append(",")
                .append(ds2).append(",")
                .append(dc2).append(",")
                .append(axis2).append(",")
                .append(pd).append(",")
                .append(mmHg).append(",")
                .append(gazeH).append(",")
                .append(gazeV).toString();
    }

    public VisionMessageData(byte[] data, Integer dataType) {

        if (dataType == 0x01) {
            this.pupil = new String(Arrays.copyOfRange(data, 0, 3), StandardCharsets.UTF_8);
            this.se1 = new String(Arrays.copyOfRange(data, 3, 8), StandardCharsets.UTF_8);
            this.ds1 = new String(Arrays.copyOfRange(data, 8, 13), StandardCharsets.UTF_8);
            this.dc1 = new String(Arrays.copyOfRange(data, 13, 18), StandardCharsets.UTF_8);
            this.axis1 = new String(Arrays.copyOfRange(data, 18, 21), StandardCharsets.UTF_8);
            this.se2 = new String(Arrays.copyOfRange(data, 21, 26), StandardCharsets.UTF_8);
            this.ds2 = new String(Arrays.copyOfRange(data, 26, 31), StandardCharsets.UTF_8);
            this.dc2 = new String(Arrays.copyOfRange(data, 31, 36), StandardCharsets.UTF_8);
            this.axis2 = new String(Arrays.copyOfRange(data, 36, 39), StandardCharsets.UTF_8);
            this.pd = new String(Arrays.copyOfRange(data, 39, 41), StandardCharsets.UTF_8);
            this.mmHg = new String(Arrays.copyOfRange(data, 41, 45), StandardCharsets.UTF_8);
            this.gazeH = new String(Arrays.copyOfRange(data, 45, 48), StandardCharsets.UTF_8);
            this.gazeV = new String(Arrays.copyOfRange(data, 48, 51), StandardCharsets.UTF_8);
        }

        this.data = data;
    }
}
