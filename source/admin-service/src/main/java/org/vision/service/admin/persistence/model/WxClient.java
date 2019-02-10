package org.vision.service.admin.persistence.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WxClient implements Serializable {
    private String id;

    private Integer publicId;

    private String appId;

    private String openId;

    private String name;

    private String wxNick;

    private String avatarImage;

    private Boolean gender;

    private Integer age;

    private Date birthday;

    private String idNumber;

    private String province;

    private String city;

    private String county;

    private String detailAddress;

    private static final long serialVersionUID = 1L;
}