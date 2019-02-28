package org.vision.service.admin.persistence.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class SystemRoleAuthority implements Serializable {
    private String roleId;

    private String authorityId;

    private Date createdTime;

    private static final long serialVersionUID = -7898186843034761891L;
}