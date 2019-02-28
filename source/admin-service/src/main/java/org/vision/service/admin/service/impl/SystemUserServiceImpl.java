package org.vision.service.admin.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.vision.service.admin.configuration.security.VisionUserDetail;
import org.vision.service.admin.persistence.mapper.SystemAuthorityMapper;
import org.vision.service.admin.persistence.mapper.SystemUserMapper;
import org.vision.service.admin.persistence.mapper.SystemUserProfileMapper;
import org.vision.service.admin.persistence.model.SystemUser;
import org.vision.service.admin.persistence.model.SystemUserProfile;
import org.vision.service.admin.persistence.model.SystemAuthority;
import org.vision.service.admin.service.SystemUserService;
import org.vision.service.admin.service.vo.SystemUserProfileVO;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service("SystemUserService")
public class SystemUserServiceImpl implements SystemUserService {

    private SystemUserMapper systemUserMapper;

    private SystemUserProfileMapper systemUserProfileMapper;

    private SystemAuthorityMapper systemAuthorityMapper;


    public SystemUserServiceImpl(SystemUserMapper systemUserMapper, SystemUserProfileMapper systemUserProfileMapper, SystemAuthorityMapper systemAuthorityMapper) {
        this.systemUserMapper = systemUserMapper;
        this.systemUserProfileMapper = systemUserProfileMapper;
        this.systemAuthorityMapper = systemAuthorityMapper;
    }

    @Override
    public UserDetails getUserByUsername(@NotNull String username) {
        SystemUser systemUser = systemUserMapper.selectByUsername(username);
        Assert.notNull(systemUser, String.format("user %s not found.", username));

        List<SystemAuthority> systemAuthorities = systemAuthorityMapper.selectByUserId(systemUser.getId());
        return new VisionUserDetail(systemUser, systemAuthorities);
    }

    @Override
    public SystemUserProfileVO getProfileById(@NotNull String systemUserId) {

        SystemUserProfile profile = systemUserProfileMapper.selectByPrimaryKey(systemUserId);
        Assert.notNull(profile, "用户信息丢失，请联系管理员");

        List<SystemAuthority> systemAuthorities = systemAuthorityMapper.selectByUserId(systemUserId);

        return new SystemUserProfileVOImpl(profile, systemAuthorities);
    }


    private class SystemUserProfileVOImpl implements SystemUserProfileVO, Serializable {

        private static final long serialVersionUID = -5416487459974681138L;

        private SystemUserProfile systemUserProfile;

        private List<SystemAuthority> systemAuthorities;

        public SystemUserProfileVOImpl(SystemUserProfile systemUserProfile, List<SystemAuthority> systemAuthorities) {
            this.systemUserProfile = systemUserProfile;
            this.systemAuthorities = systemAuthorities;
        }

        @Override
        public String getId() {
            return systemUserProfile.getId();
        }

        @Override
        public String getRealName() {
            return systemUserProfile.getName();
        }

        @Override
        public String getAvatarImage() {
            return systemUserProfile.getAvatar();
        }

        @Override
        public Byte getGender() {
            return systemUserProfile.getGender();
        }

        @Override
        public List<String> getAuthorities() {
            return systemAuthorities.parallelStream().map(SystemAuthority::getName).collect(Collectors.toList());
        }

    }

}
