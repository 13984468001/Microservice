package com.lipiao.config_center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author LIPIAO
 * @date 2021/3/24 22:57
 * @EnableConfigServer 开启配置中心服务
 */
@SuppressWarnings("all")
@EnableConfigServer
@SpringBootApplication
public class _02_config_center_v1 {
    public static void main(String[] args) {
        SpringApplication.run(_02_config_center_v1.class, args);
    }
}
