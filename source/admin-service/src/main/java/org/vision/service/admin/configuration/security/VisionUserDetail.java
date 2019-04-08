package org.vision.service.admin.configuration.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.vision.service.admin.persistence.model.SystemUser;
import org.vision.service.admin.persistence.model.SystemAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class VisionUserDetail implements UserDetails {

    private SystemUser systemUser;

    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public VisionUserDetail(SystemUser systemUser, List<SystemAuthority> systemAuthorities) {

        this.systemUser = systemUser;

        this.grantedAuthorities = systemAuthorities;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return systemUser.getPassword();
    }

    @Override
    public String getUsername() {
        return systemUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return null == systemUser.getAccountExpire() || systemUser.getAccountExpire().after(new Date());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !systemUser.getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return null == systemUser.getCredentialExpire() || systemUser.getCredentialExpire().after(new Date());
    }

    @Override
    public boolean isEnabled() {
        return systemUser.getEnabled();
    }

    public String getId() {
        return systemUser.getId();
    }
    
    public SystemUser getSystemUser() {
      return systemUser;
    }
}
