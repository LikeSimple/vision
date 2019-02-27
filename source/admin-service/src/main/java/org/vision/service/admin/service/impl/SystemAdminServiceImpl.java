package org.vision.service.admin.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.vision.service.admin.configuration.security.VisionUserDetail;
import org.vision.service.admin.persistence.mapper.SystemAdminAuthorityMapper;
import org.vision.service.admin.persistence.mapper.SystemAdminMapper;
import org.vision.service.admin.persistence.mapper.SystemAdminProfileMapper;
import org.vision.service.admin.persistence.model.SystemAdmin;
import org.vision.service.admin.persistence.model.SystemAdminProfile;
import org.vision.service.admin.persistence.model.SystemAuthority;
import org.vision.service.admin.service.AdminProfile;
import org.vision.service.admin.service.AuthorityItem;
import org.vision.service.admin.service.CurrentAdminProfile;
import org.vision.service.admin.service.SystemAdminService;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service("SystemAdminService")
public class SystemAdminServiceImpl implements SystemAdminService {

    private SystemAdminMapper systemAdminMapper;

    private SystemAdminProfileMapper systemAdminProfileMapper;

    private SystemAdminAuthorityMapper systemAdminAuthorityMapper;


    public SystemAdminServiceImpl(SystemAdminMapper systemAdminMapper, SystemAdminProfileMapper systemAdminProfileMapper, SystemAdminAuthorityMapper systemAdminAuthorityMapper) {
        this.systemAdminMapper = systemAdminMapper;
        this.systemAdminProfileMapper = systemAdminProfileMapper;
        this.systemAdminAuthorityMapper = systemAdminAuthorityMapper;
    }

    @Override
    public UserDetails getUserByUsername(@NotNull String username) {
        SystemAdmin systemAdmin = systemAdminMapper.selectByUsername(username);
        Assert.notNull(systemAdmin, String.format("user %s not found.", username));

        List<SystemAuthority> systemAuthorities = systemAdminAuthorityMapper.selectByAdminId(systemAdmin.getId());
        return new VisionUserDetail(systemAdmin, systemAuthorities);
    }

    @Override
    public CurrentAdminProfile getProfileById(@NotNull String adminId) {

        SystemAdminProfile profile = systemAdminProfileMapper.selectByPrimaryKey(adminId);
        Assert.notNull(profile, "用户信息丢失，请联系管理员");

        List<SystemAuthority> systemAuthorities = systemAdminAuthorityMapper.selectByAdminId(adminId);

        return new CurrentAdminProfileImpl(profile, systemAuthorities);
    }

    @Override
    public List<? extends AdminProfile> getAdminProfileList(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return systemAdminProfileMapper.selectAll();

    }

    private class CurrentAdminProfileImpl implements CurrentAdminProfile, Serializable {

        private static final long serialVersionUID = -5416487459974681138L;

        private SystemAdminProfile systemAdminProfile;

        private List<SystemAuthority> systemAuthorities;

        public CurrentAdminProfileImpl(SystemAdminProfile systemAdminProfile, List<SystemAuthority> systemAuthorities) {
            this.systemAdminProfile = systemAdminProfile;
            this.systemAuthorities = systemAuthorities;
        }

        @Override
        public String getId() {
            return systemAdminProfile.getId();
        }

        @Override
        public String getRealName() {
            return systemAdminProfile.getRealName();
        }

        @Override
        public String getAvatarImage() {
            return systemAdminProfile.getAvatarImage();
        }

        @Override
        public Byte getGender() {
            return systemAdminProfile.getGender();
        }

        @Override
        public List<String> getAuthorities() {
            return systemAuthorities.parallelStream().map(SystemAuthority::getName).collect(Collectors.toList());
        }

    }

}
