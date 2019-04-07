package org.vision.service.admin.controller.criteria;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SysRoleUpdateBO {

  @NotNull
  private String id;
  
  @io.swagger.annotations.ApiModelProperty(value = "角色名称", name = "roleName", required = false, example = "")
  @NotBlank
  private String name;
  
  @io.swagger.annotations.ApiModelProperty(value = "角色权限id集合", name = "systemAuthorityList", required = true, example = "")
  @NotNull
  private List<String> systemAuthorityList;

  

  
  
  
}
