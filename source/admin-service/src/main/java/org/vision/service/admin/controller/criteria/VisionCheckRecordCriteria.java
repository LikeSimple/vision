package org.vision.service.admin.controller.criteria;

import java.util.Date;

import lombok.Data;

@Data
public class VisionCheckRecordCriteria {
  
  private String schoolName;
  
  private String className;
  
  private String activityName;
  
  private String name;

  private String idNumber;
  
  private Date beginDate;

  private Date endDate;
}
