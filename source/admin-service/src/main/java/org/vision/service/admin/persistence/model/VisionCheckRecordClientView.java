package org.vision.service.admin.persistence.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class VisionCheckRecordClientView implements Serializable {

    private VisionSchool visionSchool;

    private VisionSchoolClass visionSchoolClass;

    private VisionActivity visionActivity;
    
    private VisionClient visionClient;
    
    private List<VisionCheckRecord> recordList;

    private static final long serialVersionUID = 7696842126909447626L;
}
