package org.vision.frames;

import lombok.*;
import org.vision.netty.VisionMessage;

@Data
public class VisionClientRecord {

    private String eyeType;

    private String dataType;

    private String date;

    private String time;

    private byte[] data;

    private String pupil; //瞳孔大小

    private String se1; //等效球面度

    private String ds1; //球面度

    private String dc1; //柱面度

    private String axis1; //柱面轴位角

    private String se2; //等效球面度

    private String ds2; //球面度

    private String dc2; //柱面度

    private String axis2; //柱面轴位角

    private String pd; //瞳距

    private String mmHg; //眼压

    private String gazeH; //水平固视

    private String gazeV; //垂直固视

    public VisionClientRecord(VisionMessage message) {
        this.eyeType = message.getHeader().getEyeType().equals(1)? "右眼" : "左眼";
        this.dataType = message.getHeader().getDataType().equals(1)?"数字": "图像";
        this.date = message.getHeader().getDate();
        this.time = message.getHeader().getTime();
        this.pupil = message.getData().getPupil();
        this.se1 = message.getData().getSe1();
        this.ds1 = message.getData().getDs1();
        this.dc1 = message.getData().getDc1();
        this.axis1 = message.getData().getAxis1();
        this.se2 = message.getData().getSe2();
        this.ds2 = message.getData().getDs2();
        this.dc2 = message.getData().getDc2();
        this.axis2 = message.getData().getAxis2();
        this.pd = message.getData().getPd();
        this.mmHg = message.getData().getMmHg();
        this.gazeH = message.getData().getGazeH();
        this.gazeV = message.getData().getGazeV();
    }
}
