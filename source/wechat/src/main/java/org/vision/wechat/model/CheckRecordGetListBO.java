package org.vision.wechat.model;

import lombok.Data;

@Data
public class CheckRecordGetListBO {
  
  private String visionClientId;
  
  @io.swagger.annotations.ApiModelProperty(value = "检测日期区间起始", name = "checkDateStart", required = false, example = "")
  private String checkDateStart;
  
  @io.swagger.annotations.ApiModelProperty(value = "检测日期区间闭合", name = "checkDateEnd", required = false, example = "")
  private String checkDateEnd;
  
  private int pageNum;
  
  private int pageSize;
  
  
}
