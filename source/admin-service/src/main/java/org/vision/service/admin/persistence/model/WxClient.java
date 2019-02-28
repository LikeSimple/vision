package org.vision.service.admin.persistence.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

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

    private Date createdTime;

    private Date modifiedTime;

    private static final long serialVersionUID = 8714499730885514469L;
}