package org.vision.service.admin.configuration.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.vision.service.admin.configuration.security.jwt.JwtTokenProperties;
import org.vision.service.admin.configuration.security.jwt.JwtTokenUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class RESTfulSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private JwtTokenUtil jwtTokenUtil;

    private JwtTokenProperties jwtTokenProperties;

    public RESTfulSuccessHandler(
            JwtTokenUtil jwtTokenUtil, JwtTokenProperties jwtTokenProperties) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtTokenProperties = jwtTokenProperties;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        restfulHandle(request, response, authentication);

        clearAuthenticationAttributes(request);
    }

    private void restfulHandle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //返回用户JwtToken
        response.setHeader(jwtTokenProperties.getHeader(), String.format("Bearer %s", jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal())));
    }

    @Configuration
    public static class RESTfulSuccessHandlerConfig {

        private JwtTokenUtil jwtTokenUtil;
        private JwtTokenProperties jwtTokenProperties;

        public RESTfulSuccessHandlerConfig(
                JwtTokenUtil jwtTokenUtil, JwtTokenProperties jwtTokenProperties) {
            this.jwtTokenUtil = jwtTokenUtil;
            this.jwtTokenProperties = jwtTokenProperties;
        }

        @Bean
        public RESTfulSuccessHandler resTfulSuccessHandler() {
            return new RESTfulSuccessHandler(jwtTokenUtil, jwtTokenProperties);
        }
    }

}
