package org.vision.service.admin.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.vision.service.admin.common.ResponseData;
import org.vision.service.admin.configuration.security.VisionUserDetail;
import org.vision.service.admin.service.AdminProfile;
import org.vision.service.admin.service.CurrentAdminProfile;
import org.vision.service.admin.service.SystemAdminService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class SystemAdminController {

    private SystemAdminService systemAdminService;

    public SystemAdminController(SystemAdminService systemAdminService) {
        this.systemAdminService = systemAdminService;
    }

    @GetMapping("/profile")
    public ResponseData<CurrentAdminProfile> getCurrentAdminProfile() {
        VisionUserDetail userDetail = (VisionUserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseData<>(systemAdminService.getProfileById(userDetail.getId()));
    }

    @PostMapping("/list")
    public ResponseData<List<? extends AdminProfile>> getAdminProfileList(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize
    ) {
        return new ResponseData<>(systemAdminService.getAdminProfileList(pageNumber, pageSize));
    }
}
