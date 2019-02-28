package org.vision.service.admin.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.vision.service.admin.service.vo.SystemUserProfileVO;

import javax.validation.constraints.NotNull;

@Validated
public interface SystemUserService {

    UserDetails getUserByUsername(@NotNull String username);

    SystemUserProfileVO getProfileById(@NotNull String adminId);

}
