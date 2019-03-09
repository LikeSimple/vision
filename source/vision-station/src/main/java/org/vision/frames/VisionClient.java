package org.vision.frames;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Vector;

@Data
@EqualsAndHashCode(of = {"activityId","clientId"})
@NoArgsConstructor
public class VisionClient {

    private String clientId = "";

    private String activityId = "";

    private String className = "";

    private String studentNumber = "";

    private String name = "";

    private List<VisionClientRecord> recordList = new Vector<>();

    public VisionClient(String activityId, String clientId, String className, String studentNumber, String name) {
        this.clientId = clientId;

        this.activityId = activityId;

        this.className = className;

        this.studentNumber = studentNumber;

        this.name = name;

    }


}
