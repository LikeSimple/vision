package org.vision.service.admin.service.vo;

import java.math.BigDecimal;
import java.util.Date;

public interface VisionCheckRecordVO {
  
  String getSchoolName();
  
  String getClassName();
  
  String getActivityName();
  
  String getName();

  String getIdNumber();
  
  String getId();
  
  String getVisionClientId();

  String getEyeType();

  Date getCheckDate();

  Boolean getDataType();

  String getPictureFile();

  BigDecimal getPupil();

  BigDecimal getSe1();

  BigDecimal getDs1();

  BigDecimal getDc1();

  Integer getAxis1();

  BigDecimal getSe2();

  BigDecimal getDs2();
  
  BigDecimal getDc2();

  Integer getAxis2();

  Integer getPd();

  BigDecimal getMmHg();

  Integer getGazeH();

  Integer getGazeV();

  String getRemark();

  Boolean getEnabled();

  Date getCreateTime();

  Date getModifiedTime();
  
}
