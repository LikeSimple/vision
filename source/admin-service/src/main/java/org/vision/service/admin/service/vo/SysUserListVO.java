package org.vision.service.admin.service.vo;

import java.util.Date;

import lombok.Data;

@Data
public class SysUserListVO {

  @io.swagger.annotations.ApiModelProperty(value = "",name = "id", required = false,example = "")
  private String userId;

  @io.swagger.annotations.ApiModelProperty(value = "",name = "username", required = false,example = "")
  private String userName;

  @io.swagger.annotations.ApiModelProperty(value = "",name = "password", required = false,example = "")
  private String password;

  @io.swagger.annotations.ApiModelProperty(value = "",name = "enabled", required = false,example = "")
  private Boolean enabled;

  @io.swagger.annotations.ApiModelProperty(value = "",name = "locked", required = false,example = "")
  private Boolean locked;

  @io.swagger.annotations.ApiModelProperty(value = "",name = "accountExpire", required = false,example = "")
  private Date accountExpire;

  @io.swagger.annotations.ApiModelProperty(value = "",name = "credentialExpire", required = false,example = "")
  private Date credentialExpire;
  
  private String profileId;

  private String profileName;

  private String avatar;

  private Byte gender;

  private Date createdTime;

  private Date modifiedTime;
  
}
