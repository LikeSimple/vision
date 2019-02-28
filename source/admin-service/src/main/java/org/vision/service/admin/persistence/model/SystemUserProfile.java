package org.vision.service.admin.persistence.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SystemUserProfile implements Serializable {
    private String id;

    private String name;

    private String avatar;

    private Byte gender;

    private Date createdTime;

    private Date modifiedTime;

    private static final long serialVersionUID = 5383163862354821123L;
}