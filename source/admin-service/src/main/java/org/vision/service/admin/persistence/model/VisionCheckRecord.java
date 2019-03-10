package org.vision.service.admin.persistence.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class VisionCheckRecord implements Serializable {
  
    private String checkId;
    
    private String id;

    private String visionClientId;

    private String eyeType;

    private Date checkDate;

    private Integer dataType;

    private String pictureFile;

    private BigDecimal pupil;

    private BigDecimal se1;

    private BigDecimal ds1;

    private BigDecimal dc1;

    private Integer axis1;

    private BigDecimal se2;

    private BigDecimal ds2;

    private BigDecimal dc2;

    private Integer axis2;

    private Integer pd;

    private BigDecimal mmHg;

    private Integer gazeH;

    private Integer gazeV;

    private String remark;

    private Boolean enabled;

    private Date createTime;

    private Date modifiedTime;

    private static final long serialVersionUID = -8849148213350395951L;
}