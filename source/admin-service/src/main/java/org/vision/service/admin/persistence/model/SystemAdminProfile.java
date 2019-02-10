package org.vision.service.admin.persistence.model;

import lombok.Data;
import org.vision.service.admin.service.AdminProfile;

import java.io.Serializable;
import java.util.Date;

@Data
public class SystemAdminProfile implements Serializable, AdminProfile {
    private String id;

    private String realName;

    private String avatarImage;

    private Byte gender;

    private Date createdTime;

    private Date modifiedTime;

    private static final long serialVersionUID = 1L;
}