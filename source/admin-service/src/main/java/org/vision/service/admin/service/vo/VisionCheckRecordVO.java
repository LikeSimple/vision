package org.vision.service.admin.service.vo;

import java.math.BigDecimal;
import java.util.Date;

public interface VisionCheckRecordVO {

    String getId();

    String getEyeType();

    Date checkDate();

    Integer getDataType();

    String getPictureFile();

    BigDecimal getPupil();

    BigDecimal getSe1();

    BigDecimal getDs1();

    BigDecimal getDc1();

    Integer getAxis1();

    BigDecimal getSe2();

    BigDecimal getDs2();

    BigDecimal getDc2();

    Integer getAxis2();

    Integer getPd();

    BigDecimal getMmHg();

    Integer getGazeH();

    Integer getGazeV();

}
