package org.vision.service.admin.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vision.service.admin.common.ResponseData;
import org.vision.service.admin.configuration.security.VisionUserDetail;
import org.vision.service.admin.service.SystemUserService;
import org.vision.service.admin.service.vo.SystemUserProfileVO;

@RestController
@RequestMapping("/api/user")
public class SystemUserController {

    private SystemUserService systemUserService;

    public SystemUserController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @GetMapping("/profile")
    public ResponseData<SystemUserProfileVO> getCurrentAdminProfile() {
        VisionUserDetail userDetail = (VisionUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseData<>(systemUserService.getProfileById(userDetail.getId()));
    }

}
