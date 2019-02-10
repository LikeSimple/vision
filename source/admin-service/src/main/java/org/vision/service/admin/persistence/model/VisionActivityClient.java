package org.vision.service.admin.persistence.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class VisionActivityClient implements Serializable {
    private String visionActivityId;

    private String visionClientId;

    private Boolean enabled;

    private static final long serialVersionUID = 1L;
}