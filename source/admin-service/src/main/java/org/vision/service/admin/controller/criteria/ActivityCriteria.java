package org.vision.service.admin.controller.criteria;

import lombok.Data;

import java.util.Date;

@Data
public class ActivityCriteria {

    private String nameCriteria;

    private Date beginDate;

    private Date endDate;

    private Boolean archived = false;

}
