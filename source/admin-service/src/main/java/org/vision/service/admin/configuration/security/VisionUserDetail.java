package org.vision.service.admin.configuration.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.vision.service.admin.persistence.model.SystemAdmin;
import org.vision.service.admin.persistence.model.SystemAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class VisionUserDetail implements UserDetails {

    private SystemAdmin systemAdmin;

    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public VisionUserDetail(SystemAdmin systemAdmin, List<SystemAuthority> systemAuthorities) {

        this.systemAdmin = systemAdmin;

        this.grantedAuthorities = systemAuthorities;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return systemAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return systemAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return null == systemAdmin.getAccountExpire() || systemAdmin.getAccountExpire().after(new Date());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !systemAdmin.getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return null == systemAdmin.getCredentialExpire() || systemAdmin.getCredentialExpire().after(new Date());
    }

    @Override
    public boolean isEnabled() {
        return systemAdmin.getEnabled();
    }

    public String getId() {
        return systemAdmin.getId();
    }
}
