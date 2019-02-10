package org.vision.service.admin.service;

import java.util.Date;

public interface AdminProfile {

    String getId();

    String getRealName();

    String getAvatarImage();

    Byte getGender();

    Date getCreatedTime();

    Date getModifiedTime();

}
