package org.vision.wechat.model;

import org.vision.wechat.persistence.model.VisionCheckRecordPO;

import lombok.Data;

@Data
public class CheckRecordVO {
  
  private VisionCheckRecordPO leftEye;
  
  private VisionCheckRecordPO rightEye;

  
}
