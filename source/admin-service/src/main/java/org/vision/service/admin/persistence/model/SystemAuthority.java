package org.vision.service.admin.persistence.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class SystemAuthority implements GrantedAuthority, Serializable {
    private String id;

    private String name;

    private String desc;

    private Date createdTime;

    private Date modifiedTime;

    @Override
    public String getAuthority() {
        return name;
    }

    private static final long serialVersionUID = 1821794625333295036L;
}