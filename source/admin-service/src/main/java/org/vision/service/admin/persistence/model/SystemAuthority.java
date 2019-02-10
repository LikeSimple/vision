package org.vision.service.admin.persistence.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;

@Data
public class SystemAuthority implements GrantedAuthority {
    private String id;

    private String name;

    private String desc;

    private Date createdTime;

    private Date modifiedTime;

    private static final long serialVersionUID = 1L;

    @Override
    public String getAuthority() {
        return name;
    }
}