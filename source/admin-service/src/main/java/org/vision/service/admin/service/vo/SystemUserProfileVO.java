package org.vision.service.admin.service.vo;

import java.util.List;

public interface SystemUserProfileVO {

    String getId();

    String getRealName();

    String getAvatarImage();

    Byte getGender();

    List<String> getAuthorities();

}
