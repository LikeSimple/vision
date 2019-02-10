package org.vision.service.admin.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.vision.service.admin.service.SystemAdminService;

public class VisionUserDetailsService implements UserDetailsService {

    private SystemAdminService systemAdminService;

    public VisionUserDetailsService(SystemAdminService systemAdminService) {
        this.systemAdminService = systemAdminService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            return systemAdminService.getUserByUsername(username);

        } catch (IllegalArgumentException e) {
            throw new UsernameNotFoundException(String.format("user %s not found.", username));
        }

    }

    @Configuration
    public static class VisionUserDetailsServiceConfiguration {

        private SystemAdminService systemAdminService;

        public VisionUserDetailsServiceConfiguration(SystemAdminService systemAdminService) {
            this.systemAdminService = systemAdminService;
        }

        @Bean
        public VisionUserDetailsService visionUserDetailsService() {
            return new VisionUserDetailsService(systemAdminService);
        }

    }
}
