package org.vision.wechat.model;

import java.util.Date;

import lombok.Data;

@Data
public class CheckRecordListBO {
  
  private String clientName;
  private String idNumber;
  private String activityName;
  private String clientId;
  private String activityId;
  private String id;
  private String eyeType;
  private Date checkDate;
  
}
