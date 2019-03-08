package main.java.org.vision.frames;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Vector;

@Data
@EqualsAndHashCode(of = {"activityId","clientId"})
public class VisionClient {

    private String clientId;

    private String activityId;

    private String className;

    private String studentNumber;

    private String name;

    private List<VisionClientRecord> recordList = new Vector<>();

    public VisionClient(String activityId, String clientId, String className, String studentNumber, String name) {
        this.clientId = clientId;

        this.activityId = activityId;

        this.className = className;

        this.studentNumber = studentNumber;

        this.name = name;

    }


}
