package org.vision.service.admin.controller.criteria;

import java.util.List;

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
  
  @io.swagger.annotations.ApiModelProperty(value = "密码", name = "password", required = false, example = "")
  @NotBlank
  private String password;

  @io.swagger.annotations.ApiModelProperty(value = "手机号", name = "phone", required = false, example = "")
  @NotBlank
  private String phone;

  @io.swagger.annotations.ApiModelProperty(value = "岗位", name = "position", required = false, example = "")
  private String position;
  
  @io.swagger.annotations.ApiModelProperty(value = "角色id集合", name = "sysRoleIdList", required = true, example = "")
  @NotNull
  private List<String> sysRoleIdList;
  
  @io.swagger.annotations.ApiModelProperty(value = "备注", name = "remark", required = false, example = "")
  private String remark;
  
}
