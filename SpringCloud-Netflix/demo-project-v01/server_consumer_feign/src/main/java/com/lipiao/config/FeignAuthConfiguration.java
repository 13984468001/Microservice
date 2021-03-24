package com.lipiao.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * 服务提供者开启安全验证后，需要配置Feign调用远程服务需要认证
 *
 * @author LIPIAO
 * @date 2021/3/23 20:41
 */
public class FeignAuthConfiguration {

    @Value("${spring.security.user.password}")
    private String user;
    @Value("${spring.security.user.name}")
    private String pwd;

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(user, pwd);
    }
}