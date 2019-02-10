package org.vision.service.admin.service;

import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface SystemResourceService {

    List<ResourceItem> getSystemResources();


}
