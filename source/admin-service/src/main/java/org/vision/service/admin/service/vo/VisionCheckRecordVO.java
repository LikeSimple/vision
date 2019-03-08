package org.vision.service.admin.service.vo;

import org.vision.service.admin.persistence.model.VisionCheckRecord;

public interface VisionCheckRecordVO {
  
  String getSchoolName();
  
  String getClassName();
  
  String getActivityName();
  
  String getName();

  String getIdNumber();
  
  VisionCheckRecord getLeftEye();
  
  VisionCheckRecord getRightEye();

  
  
}
