package org.vision.service.admin.persistence.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class VisionClient implements Serializable {
    private String id;

    private String wxClientId;

    private String name;

    private Boolean gender;

    private Integer age;

    private String idNumber;

    private String nativePlace;

    private BigDecimal height;

    private BigDecimal weight;

    private Date birthday;

    private String phoneNumber;

    private String province;

    private String city;

    private String county;

    private String detailAddress;

    private BigDecimal visionAcuityLeft;

    private BigDecimal visionAcuityRight;

    private BigDecimal visionAcuity;

    private Integer dioptersLeft;

    private Integer dioptersRight;

    private Integer astigmatismLeft;

    private Integer astigmatismRight;

    private Integer jointLuminosityLeft;

    private Integer jointLuminosityRight;

    private Integer axisLeft;

    private Integer axisRight;

    private Integer pupilDistance;

    private Date createdTime;

    private Date modifiedTime;

    private static final long serialVersionUID = -4602695220598495960L;
}