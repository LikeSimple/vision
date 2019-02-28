package org.vision.service.admin.persistence.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class SystemResource implements Serializable {
    private String id;

    private String name;

    private String serialKey;

    private String url;

    private String icon;

    private String remark;

    private Boolean enabled;

    private Date createdTime;

    private Date modifiedTime;

    private static final long serialVersionUID = -9103423238783360077L;
}