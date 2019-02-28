package org.vision.service.admin.persistence.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class VisionSchool implements Serializable {
    private String id;

    private String name;

    private String province;

    private String county;

    private String city;

    private String detailAddress;

    private Date createdTime;

    private Date modifiedTime;

    private static final long serialVersionUID = -6683317130594891254L;
}