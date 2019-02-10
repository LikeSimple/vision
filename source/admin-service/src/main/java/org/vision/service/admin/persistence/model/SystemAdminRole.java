package org.vision.service.admin.persistence.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SystemAdminRole implements Serializable {
    private String adminId;

    private String roleId;

    private Date createdTime;

    private static final long serialVersionUID = 1L;
}