package org.vision.service.admin.persistence.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class VisionClientCheckRecordView implements Serializable {

    private VisionActivity visionActivity;

    private VisionActivityClient visionActivityClient;

    private VisionClient visionClient;

    private VisionSchoolClassMember visionSchoolClassMember;

    private VisionSchoolClass visionSchoolClass;

    private VisionSchool visionSchool;

    private List<VisionCheckRecord> visionCheckRecordList;

    private static final long serialVersionUID = 7696842126909447626L;
}
