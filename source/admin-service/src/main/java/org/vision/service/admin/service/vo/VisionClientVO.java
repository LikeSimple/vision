package org.vision.service.admin.service.vo;

import java.math.BigDecimal;
import java.util.Date;

public interface VisionClientVO {

    String getSchoolName();

    String getClassName();

    String getSchoolStudentNumber();

    String getClientId();

    String getName();

    Integer getGender();

    Integer getAge();

    String getIdNumber();

    String getNativePlace();

    BigDecimal getHeight();

    BigDecimal getWeight();

    Date getBirthday();

    String getPhoneNumber();

    String getProvince();

    String getCity();

    String county();

    String getDetailAddress();

    BigDecimal getVisionAcuityLeft();

    BigDecimal getVisionAcuityRight();

    BigDecimal getVisionAcuity();

    String getStudentNumber();
}
