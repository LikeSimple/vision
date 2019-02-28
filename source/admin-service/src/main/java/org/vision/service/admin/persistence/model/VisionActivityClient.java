package org.vision.service.admin.persistence.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class VisionActivityClient implements Serializable {
    private String visionActivityId;

    private String visionClientId;

    private Boolean enabled;

    private Date createdTime;

    private static final long serialVersionUID = 172619495739284103L;
}