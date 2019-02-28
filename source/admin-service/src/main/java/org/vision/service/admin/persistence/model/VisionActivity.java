package org.vision.service.admin.persistence.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class VisionActivity implements Serializable {
    private String id;

    private String name;

    private String address;

    private Date beginDate;

    private Date endDate;

    private String content;

    private String contactMan;

    private String contactPhoneNumber;

    private String remark;

    private Boolean archived;

    private Date modifiedTime;

    private Date createdTime;

    private static final long serialVersionUID = 5305118938854500301L;
}