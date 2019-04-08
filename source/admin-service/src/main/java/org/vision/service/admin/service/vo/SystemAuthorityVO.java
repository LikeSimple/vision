package org.vision.service.admin.service.vo;

import org.vision.service.admin.persistence.model.SystemAuthority;

import lombok.Data;

@Data
public class SystemAuthorityVO extends SystemAuthority {

  private boolean authorize;
}
