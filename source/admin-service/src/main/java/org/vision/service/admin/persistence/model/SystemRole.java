package org.vision.service.admin.persistence.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class SystemRole implements Serializable {
    private String id;

    private String name;

    private String desc;

    private Date createdTime;

    private Date modifiedTime;

    private static final long serialVersionUID = -2846872922031822825L;
}