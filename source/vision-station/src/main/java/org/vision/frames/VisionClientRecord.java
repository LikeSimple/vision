package main.java.org.vision.frames;

import lombok.*;

@Data
public class VisionClientRecord {

    private String eyeType;

    private String date;

    private String time;

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

}
