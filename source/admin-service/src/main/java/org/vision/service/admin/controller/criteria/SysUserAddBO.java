package org.vision.service.admin.controller.criteria;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SysUserAddBO {

  @io.swagger.annotations.ApiModelProperty(value = "登录名", name = "name", required = false, example = "")
  @NotBlank
  private String loginName;
  
  @io.swagger.annotations.ApiModelProperty(value = "密码", name = "password", required = false, example = "")
  @NotBlank
  private String password;

  private String name;

  private String avatar;

  private Byte gender;

}
