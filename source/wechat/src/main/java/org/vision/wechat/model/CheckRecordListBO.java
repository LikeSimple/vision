package org.vision.wechat.model;

import java.util.Date;

import lombok.Data;

@Data
public class CheckRecordListBO {
  
  private String activityName;
  private String id;
  private String eyeType;
  private Date checkDate;
  
}
