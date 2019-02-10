package org.vision.service.admin.service;

import java.util.List;

public interface CurrentAdminProfile {

    String getId();

    String getRealName();

    String getAvatarImage();

    Byte getGender();

    List<AuthorityItem> getAuthorities();

}
