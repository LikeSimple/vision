package org.vision.service.admin.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.vision.service.admin.common.ResponseData;
import org.vision.service.admin.configuration.security.jwt.JwtTokenProperties;
import org.vision.service.admin.configuration.security.jwt.JwtTokenUtil;
import org.vision.service.admin.service.CurrentAdminProfile;
import org.vision.service.admin.service.SystemAdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class RESTfulSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private ObjectMapper jacksonObjectMapper;

    private SystemAdminService systemAdminService;

    private JwtTokenUtil jwtTokenUtil;

    private JwtTokenProperties jwtTokenProperties;

    public RESTfulSuccessHandler(ObjectMapper jacksonObjectMapper, SystemAdminService systemAdminService, JwtTokenUtil jwtTokenUtil, JwtTokenProperties jwtTokenProperties) {
        this.jacksonObjectMapper = jacksonObjectMapper;
        this.systemAdminService = systemAdminService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtTokenProperties = jwtTokenProperties;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        restfulHandle(request, response, authentication);

        clearAuthenticationAttributes(request);
    }

    private void restfulHandle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        //获取登录的客户
        VisionUserDetail visionUserDetail = (VisionUserDetail) authentication.getPrincipal();
        response.setHeader(jwtTokenProperties.getHeader(), String.format("Bearer %s", jwtTokenUtil.generateToken(visionUserDetail)));
        CurrentAdminProfile currentAdminProfile = systemAdminService.getProfileById(visionUserDetail.getId());
        jacksonObjectMapper.writeValue(out, new ResponseData<>(currentAdminProfile));
        out.flush();
    }

    @Configuration
    public static class RESTfulSuccessHandlerConfig {

        private ObjectMapper jacksonObjectMapper;
        private SystemAdminService systemAdminService;
        private JwtTokenUtil jwtTokenUtil;
        private JwtTokenProperties jwtTokenProperties;

        public RESTfulSuccessHandlerConfig(ObjectMapper jacksonObjectMapper, SystemAdminService systemAdminService, JwtTokenUtil jwtTokenUtil, JwtTokenProperties jwtTokenProperties) {
            this.jacksonObjectMapper = jacksonObjectMapper;
            this.systemAdminService = systemAdminService;
            this.jwtTokenUtil = jwtTokenUtil;
            this.jwtTokenProperties = jwtTokenProperties;
        }

        @Bean
        public RESTfulSuccessHandler resTfulSuccessHandler() {
            return new RESTfulSuccessHandler(jacksonObjectMapper, systemAdminService, jwtTokenUtil, jwtTokenProperties);
        }
    }

}
