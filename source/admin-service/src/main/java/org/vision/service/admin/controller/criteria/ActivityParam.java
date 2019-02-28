package org.vision.service.admin.controller.criteria;

import lombok.Data;

import java.util.Date;

@Data
public class ActivityParam {

    private String name;

    private String address;

    private Date beginDate;

    private Date endDate;

    private String content;

    private String contactMan;

    private String contactPhoneNumber;

    private String remark;
}
