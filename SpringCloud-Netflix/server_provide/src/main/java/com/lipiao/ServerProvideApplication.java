package com.lipiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//新版本可不加
@EnableEurekaClient
@SpringBootApplication
class ServerProvideApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerProvideApplication.class, args);
    }

}
