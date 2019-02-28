package org.vision.service.admin.persistence.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SystemUser implements Serializable {

    private String id;

    private String username;

    private String password;

    private Boolean enabled;

    private Boolean locked;

    private Date accountExpire;

    private Date credentialExpire;

    private Date createdTime;

    private Date modifiedTime;

    private static final long serialVersionUID = 8716603741779986782L;
}