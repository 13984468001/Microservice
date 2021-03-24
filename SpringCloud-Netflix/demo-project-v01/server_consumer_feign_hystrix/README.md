# 工程简介

1. openfeign 服务消费方
    
1.1. feign默认集成了ribbon(负载均衡)和hystrix(熔断)

1.1.1. 配置服务熔断降级、和监控仪表盘

    <!-- Hystrix 熔断 降级 -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
    </dependency>
    <!-- Hystrix 仪表盘 -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
    </dependency>
    
1.1.2. feign使用fallback、fallbackFactory整合Hystrix

1.1.3. RestTemplate使用“@HystrixCommand(fallbackMethod = "back")”整合，back是一个方法，与接口方法定义在同一个类中

1.2. SpringCloud Admin 健康检查

1.2.1. 依赖

    <!-- SpringCloud Admin 监控检材 Client 端 -->
    <dependency>
        <groupId>de.codecentric</groupId>
        <artifactId>spring-boot-admin-starter-client</artifactId>
        <version>2.3.1</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    
1.2.2. 配置

    #Spring Boot2.x Actuator监控应用，开启所有端点
    management.endpoints.web.exposure.include=*
    #总是显示完整的健康信息，用于SpringCloud Admin检查
    management.endpoint.health.show-details=always
    #连接SpringCloud Admin 服务端
    spring.boot.admin.client.url=http://192.168.88.1:9090