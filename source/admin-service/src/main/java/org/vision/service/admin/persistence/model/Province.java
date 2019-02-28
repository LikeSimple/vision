package org.vision.service.admin.persistence.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Province implements Serializable {
    private String id;

    private String name;

    private Date createdTime;

    private Date modifiedTime;

    private static final long serialVersionUID = 1626105135758504001L;
}