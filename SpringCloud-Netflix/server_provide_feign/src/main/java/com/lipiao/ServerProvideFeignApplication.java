package com.lipiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
class ServerProvideFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerProvideFeignApplication.class, args);
    }

}
