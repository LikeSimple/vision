package org.vision.service.admin.controller.criteria;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SysRoleAddCriteria {

  @io.swagger.annotations.ApiModelProperty(value = "角色名称", name = "roleName", required = false, example = "")
  @NotBlank
  private String roleName;
  
  @io.swagger.annotations.ApiModelProperty(value = "系统资源id集合", name = "systemAuthorityList", required = true, example = "")
  @NotNull
  private List<String> systemAuthorityList;
  
}
