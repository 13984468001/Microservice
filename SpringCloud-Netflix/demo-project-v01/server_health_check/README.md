# 工程简介

1. SpringCloud Admin健康检查 服务
    
1.1. 服务端引入依赖

    <!-- SpringCloud Admin健康检查 服务服务端、UI -->
    <dependency>
        <groupId>de.codecentric</groupId>
        <artifactId>spring-boot-admin-starter-server</artifactId>
    </dependency>
    <dependency>
        <groupId>de.codecentric</groupId>
        <artifactId>spring-boot-admin-server-ui</artifactId>
    </dependency>
    
1.2. 启动类加注解：@EnableAdminServer

1.3. 配置详见 application.properties

1.4. 集成邮件通知、钉钉通知