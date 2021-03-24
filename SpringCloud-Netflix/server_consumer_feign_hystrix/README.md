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