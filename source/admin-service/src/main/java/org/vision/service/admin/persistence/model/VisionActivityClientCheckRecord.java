package org.vision.service.admin.persistence.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class VisionActivityClientCheckRecord implements Serializable {
    private String visionClientId;

    private String visionActivityId;

    private String visionCheckRecordId;

    private Date createdTime;

    private static final long serialVersionUID = 1422467881947684323L;
}