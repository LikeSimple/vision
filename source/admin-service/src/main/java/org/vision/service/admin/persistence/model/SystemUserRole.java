package org.vision.service.admin.persistence.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class SystemUserRole implements Serializable {
    private String systemUserId;

    private String roleId;

    private Date createdTime;

    private static final long serialVersionUID = -9017197279407526933L;
}