package org.vision.service.admin.persistence.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class City implements Serializable {
    private String id;

    private String countyId;

    private String name;

    private Date createdTime;

    private Date modifiedTime;

    private static final long serialVersionUID = 6913707600965333846L;
}