package org.vision.service.admin.persistence.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class VisionClient implements Serializable {
    private String id;

    private String wxClientId;

    private String name;

    private Integer age;

    private Date birthday;

    private String phoneNumber;

    private String province;

    private String city;

    private String county;

    private String detailAddress;

    private Boolean gender;

    private String idNumber;

    private static final long serialVersionUID = 1L;
}