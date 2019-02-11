package org.vision.service.admin.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.vision.service.admin.configuration.security.VisionUserDetail;
import org.vision.service.admin.service.AdminProfile;
import org.vision.service.admin.service.CurrentAdminProfile;
import org.vision.service.admin.service.SystemAdminService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class SystemAdminController {

    private SystemAdminService systemAdminService;

    public SystemAdminController(SystemAdminService systemAdminService) {
        this.systemAdminService = systemAdminService;
    }

    @GetMapping("/profile")
    public CurrentAdminProfile getCurrentAdminProfile() {
        VisionUserDetail userDetail = (VisionUserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return systemAdminService.getProfileById(userDetail.getId());
    }

    @PostMapping("/list")
    public List<? extends AdminProfile> getAdminProfileList(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize
    ) {
        return systemAdminService.getAdminProfileList(pageNumber, pageSize);
    }
}
