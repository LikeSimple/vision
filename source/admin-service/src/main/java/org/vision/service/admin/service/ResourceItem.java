package org.vision.service.admin.service;

import java.util.List;

public interface ResourceItem {

    String getName();

    String getURL();

    String getIcon();

    List<AuthorityItem> getAuthorities();

}
