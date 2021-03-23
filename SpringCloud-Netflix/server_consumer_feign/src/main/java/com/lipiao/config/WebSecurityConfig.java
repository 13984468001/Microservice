package com.lipiao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author LIPIAO
 * @date 2021/3/23 20:36
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭csrf。跨站点请求伪造(Cross Site Request Forgery)
        http.csrf().disable();
        //所有访问必须经过认证后方可进行
        http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
        //所有的rest服务设置为无状态，提升操作效率和性能
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //super.configure(http);
    }

}
