package org.vision.service.admin.service.vo;

import java.util.List;

import org.vision.service.admin.persistence.model.SystemRole;
import org.vision.service.admin.persistence.model.SystemUser;

import lombok.Data;

@Data
public class SysUserVO {
  
  private SystemUser systemUser;
  
  private List<SystemRole> roleList;

}
