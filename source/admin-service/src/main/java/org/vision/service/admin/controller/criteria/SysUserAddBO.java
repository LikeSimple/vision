package org.vision.service.admin.controller.criteria;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SysUserAddBO {

  @io.swagger.annotations.ApiModelProperty(value = "姓名", name = "name", required = false, example = "")
  @NotBlank
  private String name;
  
  @io.swagger.annotations.ApiModelProperty(value = "密码", name = "password", required = false, example = "")
  @NotBlank
  private String password;
  
  @io.swagger.annotations.ApiModelProperty(value = "角色id集合", name = "sysRoleIdList", required = true, example = "")
  @NotNull
  private List<String> sysRoleIdList;

}
