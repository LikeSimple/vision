package org.vision.service.admin.persistence.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.vision.service.admin.controller.criteria.ActivityParam;
import org.vision.service.admin.util.ShortUUIDGenerator;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
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

    public VisionActivity(ActivityParam activityParam) {
        BeanUtils.copyProperties(activityParam, this);
        this.id = ShortUUIDGenerator.newID();
        this.createdTime = new Date();
    }
}