package org.vision.service.admin.persistence.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class VisionActivityClient implements Serializable {
    private String visionActivityId;

    private String visionClientId;

    private String visionMemberId;

    private Boolean enabled;

    private Date createdTime;

    private static final long serialVersionUID = 1L;

    public VisionActivityClient(String activityId, String clientId) {
        this.visionActivityId = activityId;

        this.visionClientId = clientId;
    }
}