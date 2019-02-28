package org.vision.service.admin.persistence.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class VisionSchoolClass implements Serializable {
    private String id;

    private String visionSchoolId;

    private String name;

    private Date createdTime;

    private Date modifiedTime;

    private static final long serialVersionUID = -7633414036133261152L;
}