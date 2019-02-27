package org.vision.service.admin.configuration.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.vision.service.admin.configuration.security.jwt.JwtAuthenticationTokenFilter;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private RestfulSuccessHandler resTfulSuccessHandler;
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


    public ApiSecurityConfig(@Qualifier("visionUserDetailsService") UserDetailsService userDetailsService,
                             RestfulSuccessHandler resTfulSuccessHandler,
                             JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter) {
        this.userDetailsService = userDetailsService;
        this.resTfulSuccessHandler = resTfulSuccessHandler;
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
    }

    // 必须覆盖该方法以便使自定义安全配置起效果
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(4));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭CSRF
        http.csrf().disable();
        // Restful无状态风格
        http.exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().formLogin().loginPage("/api/user/login").permitAll().successHandler(resTfulSuccessHandler)
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and().logout().logoutUrl("/api/user/logout").logoutSuccessHandler(new RestfulLogoutSuccessHandler());
        // 添加JwtToken验证过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 安全访问入口
        http.authorizeRequests().antMatchers("/api/**").fullyAuthenticated();
    }
}
