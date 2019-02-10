package org.vision.service.admin.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vision.service.admin.service.AdminProfile;
import org.vision.service.admin.service.SystemAdminService;

import java.util.List;

@RestController
@RequestMapping("/api/system-admin")
public class SystemAdminController {

    private SystemAdminService systemAdminService;

    public SystemAdminController(SystemAdminService systemAdminService) {
        this.systemAdminService = systemAdminService;
    }

    @PostMapping("/list")
    public List<? extends AdminProfile> getAdminProfileList(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize
    ) {
        return systemAdminService.getAdminProfileList(pageNumber, pageSize);
    }
}
