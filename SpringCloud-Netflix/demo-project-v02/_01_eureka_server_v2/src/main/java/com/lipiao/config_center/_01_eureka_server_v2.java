package com.lipiao.config_center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author LIPIAO
 * @date 2021/3/24 22:57
 * @EnableEurekaServer 开启服务发现注册
 */
@SuppressWarnings("all")
@EnableEurekaServer
@SpringBootApplication
public class _01_eureka_server_v2 {

    public static void main(String[] args) {
        SpringApplication.run(_01_eureka_server_v2.class, args);
    }

}
