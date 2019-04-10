package org.vision.service.admin.service.vo;

import org.vision.service.admin.persistence.model.SystemRole;

import lombok.Data;
@Data
public class SysUserRoleVO extends SystemRole {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private boolean add;

}
