package org.vision.service.admin.persistence.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class VisionActivityClientView implements Serializable {

    private VisionActivity visionActivity;

    private VisionActivityClient visionActivityClient;

    private VisionSchool visionSchool;

    private VisionSchoolClass visionSchoolClass;

    private VisionSchoolClassMember visionSchoolClassMember;

    private VisionClient visionClient;

    private static final long serialVersionUID = 7696842126909447626L;
}
