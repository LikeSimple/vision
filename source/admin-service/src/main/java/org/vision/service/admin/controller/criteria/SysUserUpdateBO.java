package org.vision.service.admin.controller.criteria;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class SysUserUpdateBO {

  @io.swagger.annotations.ApiModelProperty(value = "用户id", name = "sysUserId", required = false, example = "")
  @NotNull
  private String sysUserId;
  
  @io.swagger.annotations.ApiModelProperty(value = "姓名", name = "name", required = false, example = "")
  @NotBlank
  private String name;

  private String avatar;

  private Byte gender;
  
}
