package com.lipiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author LIPIAO
 * @date 2021/3/24 11:20
 * @EnableEurekaClient 新版本可不加
 */
@EnableEurekaClient
@SpringBootApplication
public class ServerConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerConsumerApplication.class, args);
    }
}
