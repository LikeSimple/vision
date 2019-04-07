package org.vision.service.admin.controller.criteria;

import lombok.Data;

@Data
public class SysUserGetListBO {

  @io.swagger.annotations.ApiModelProperty(value = "用户code", name = "sysUserCode", required = false, example = "")
  private String sysUserCode;

  @io.swagger.annotations.ApiModelProperty(value = "手机号", name = "phone", required = false, example = "")
  private String phone;

  @io.swagger.annotations.ApiModelProperty(value = "姓名", name = "name", required = false, example = "")
  private String name;
  
  private int pageNum;
  
  private int pageSize;

  
  
  
}
