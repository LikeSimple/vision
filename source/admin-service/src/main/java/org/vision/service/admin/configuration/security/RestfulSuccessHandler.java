package org.vision.service.admin.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.vision.service.admin.common.ResponseData;
import org.vision.service.admin.configuration.security.jwt.JwtTokenUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class RestfulSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private JwtTokenUtil jwtTokenUtil;


    private ObjectMapper objectMapper;

    public RestfulSuccessHandler(
            JwtTokenUtil jwtTokenUtil,  ObjectMapper objectMapper) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        restfulHandle(request, response, authentication);

        clearAuthenticationAttributes(request);
    }

    private void restfulHandle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //返回用户JwtToken
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        objectMapper.writeValue(printWriter, new ResponseData<>(new Token(jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal()))));
        printWriter.flush();
    }

    private class Token {
        private String token;

        public Token(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }

    @Configuration
    public static class RESTfulSuccessHandlerConfig {

        private JwtTokenUtil jwtTokenUtil;
        private ObjectMapper objectMapper;

        public RESTfulSuccessHandlerConfig(
                JwtTokenUtil jwtTokenUtil, ObjectMapper objectMapper) {
            this.jwtTokenUtil = jwtTokenUtil;
            this.objectMapper = objectMapper;
        }

        @Bean
        public RestfulSuccessHandler resTfulSuccessHandler() {
            return new RestfulSuccessHandler(jwtTokenUtil, objectMapper);
        }
    }

}
