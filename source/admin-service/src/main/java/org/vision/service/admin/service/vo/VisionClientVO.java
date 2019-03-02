package org.vision.service.admin.service.vo;

import java.math.BigDecimal;
import java.util.Date;

public interface VisionClientVO {

    String getSchoolName();

    String getClassName();

    String getStudentNumber();

    String getClientId();

    String getName();

    Byte getGender();

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

    Integer getDioptersLeft();

    Integer getDioptersRight();

    Integer getAstigmatismLeft();

    Integer getAstigmatismRight();

    Integer getJoinLuminosityLeft();

    Integer getJoinLuminosityRight();

    Integer getAxisLeft();

    Integer getAxisRight();

    Integer getPupilDistance();
}
