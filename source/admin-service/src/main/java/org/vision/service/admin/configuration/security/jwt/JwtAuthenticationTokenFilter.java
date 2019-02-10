package org.vision.service.admin.configuration.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.vision.service.admin.configuration.security.VisionUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private JwtTokenProperties jwtTokenProperties;

    private JwtTokenUtil jwtTokenUtil;

    private VisionUserDetailsService visionUserDetailsService;

    public JwtAuthenticationTokenFilter(JwtTokenProperties jwtTokenProperties, JwtTokenUtil jwtTokenUtil, VisionUserDetailsService visionUserDetailsService) {
        this.jwtTokenProperties = jwtTokenProperties;
        this.jwtTokenUtil = jwtTokenUtil;
        this.visionUserDetailsService = visionUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 从这里开始获取 request 中的 jwt token
        String authHeader = request.getHeader(jwtTokenProperties.getHeader());
        log.info("{}：{}", jwtTokenProperties.getHeader(), authHeader);
        // 验证token是否存在
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            // 根据token 获取用户名
            String username = jwtTokenUtil.getUsernameFromToken(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 通过用户名 获取用户的信息
                UserDetails userDetails = this.visionUserDetailsService.loadUserByUsername(username);
                // 验证token和用户是否匹配
                if (jwtTokenUtil.validateToken(token, userDetails)) {
                    // 然后把构造UsernamePasswordAuthenticationToken对象
                    // 最后绑定到当前request中，在后面的请求中就可以获取用户信息
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
