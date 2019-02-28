package org.vision.service.admin.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.vision.service.admin.service.SystemUserService;

public class VisionUserDetailsService implements UserDetailsService {

    private SystemUserService systemUserService;

    public VisionUserDetailsService(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            return systemUserService.getUserByUsername(username);

        } catch (IllegalArgumentException e) {
            throw new UsernameNotFoundException(String.format("user %s not found.", username));
        }

    }

    @Configuration
    public static class VisionUserDetailsServiceConfiguration {

        private SystemUserService systemUserService;

        public VisionUserDetailsServiceConfiguration(SystemUserService systemUserService) {
            this.systemUserService = systemUserService;
        }

        @Bean
        public VisionUserDetailsService visionUserDetailsService() {
            return new VisionUserDetailsService(systemUserService);
        }

    }
}
