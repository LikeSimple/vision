package org.vision.service.admin.service.vo;

import java.util.Date;

public interface VisionActivityVO {

    String getId();

    String getName();

    String getAddress();

    Date getBeginDate();

    Date getEndDate();

    String getDateRange();

    String getContent();

    String getContactMan();

    String getContactPhoneNumber();

    String getRemark();

    String getCreatedTime();

    String getModifiedTime();
}
