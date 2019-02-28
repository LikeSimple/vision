package org.vision.service.admin.persistence.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class County implements Serializable {
    private String id;

    private String name;

    private String provinceId;

    private Date createdTime;

    private Date modifiedTime;

    private static final long serialVersionUID = -5669081919288272673L;
}