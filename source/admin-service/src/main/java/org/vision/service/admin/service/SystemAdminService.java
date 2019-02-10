package org.vision.service.admin.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface SystemAdminService {

    UserDetails getUserByUsername(@NotNull String username);

    CurrentAdminProfile getProfileById(@NotNull String adminId);

    List<? extends AdminProfile> getAdminProfileList(int pageNumber, int pageSize);
}
