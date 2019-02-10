package org.vision.service.admin.persistence.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class VisionActivityClientCheckReport implements Serializable {
    private String visionClientId;

    private String visionActivityId;

    private String visionCheckReportId;

    private static final long serialVersionUID = 1L;
}