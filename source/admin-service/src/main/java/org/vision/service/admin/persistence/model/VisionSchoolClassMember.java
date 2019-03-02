package org.vision.service.admin.persistence.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class VisionSchoolClassMember implements Serializable {
    private String id;

    private String visionClassId;

    private String studentNumber;

    private String visionClientId;

    private Date createdTime;

    private Date modifiedTime;

    private static final long serialVersionUID = 1L;
}