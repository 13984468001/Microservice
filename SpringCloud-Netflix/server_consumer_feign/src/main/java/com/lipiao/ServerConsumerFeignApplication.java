package com.lipiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author LIPIAO
 * @date 2021/3/23 17:50
 * <p>
 * @EnableFeignClients像是一个开关，只有使用了该注解，OpenFeign相关的组件和配置机制才会生效。还可以对OpenFeign相关组件进行自定义配置 </p>
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
class ServerConsumerFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerConsumerFeignApplication.class, args);
    }

}
