package com.lipiao;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LIPIAO
 * @date 2021/3/23 17:50
 * <p>
 * @EnableAdminServer 开启健康检查
 */
@EnableAdminServer
@SpringBootApplication
public class ServerHealthCheckApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerHealthCheckApplication.class, args);
    }
}
