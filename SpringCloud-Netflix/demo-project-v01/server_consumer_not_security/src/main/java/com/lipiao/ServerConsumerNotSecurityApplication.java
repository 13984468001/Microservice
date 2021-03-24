package com.lipiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author LIPIAO
 * @date 2021/3/23 17:50
 * <p>
 * @EnableFeignClients 像是一个开关，只有使用了该注解，OpenFeign相关的组件和配置机制才会生效。还可以对OpenFeign相关组件进行自定义配置
 * @EnableHystrixDashboard Feign集成了Hystrix，这里是开启Hystrix仪表盘
 * @EnableCircuitBreaker hystrix整合RestTemplate
 */
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class ServerConsumerNotSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerConsumerNotSecurityApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        //restTemplate.getInterceptors().add(new LoggingClientHttpRequestInterceptor());
        return restTemplate;
    }

}
