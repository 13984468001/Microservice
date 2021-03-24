package com.lipiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerActuatorSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerActuatorSecurityApplication.class, args);
    }

}
