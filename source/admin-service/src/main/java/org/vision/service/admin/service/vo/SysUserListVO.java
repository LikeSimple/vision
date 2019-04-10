package org.vision.service.admin.service.vo;

import java.util.Date;

import lombok.Data;

@Data
public class SysUserListVO {

  @io.swagger.annotations.ApiModelProperty(value = "",name = "id", required = false,example = "")
  private String userId;

  @io.swagger.annotations.ApiModelProperty(value = "",name = "loginName", required = false,example = "")
  private String loginName;

  @io.swagger.annotations.ApiModelProperty(value = "",name = "enabled", required = false,example = "")
  private Boolean enabled;

  @io.swagger.annotations.ApiModelProperty(value = "",name = "locked", required = false,example = "")
  private Boolean locked;

  @io.swagger.annotations.ApiModelProperty(value = "",name = "accountExpire", required = false,example = "")
  private Date accountExpire;

  @io.swagger.annotations.ApiModelProperty(value = "",name = "credentialExpire", required = false,example = "")
  private Date credentialExpire;

  private String name;

  private String avatar;

  private Byte gender;

  private Date createdTime;

  private Date modifiedTime;
  
}
