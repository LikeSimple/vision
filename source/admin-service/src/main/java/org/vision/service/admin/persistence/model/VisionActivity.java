package org.vision.service.admin.persistence.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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

    private static final long serialVersionUID = 1L;
}