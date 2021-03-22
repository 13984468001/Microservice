package com.lipiao.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LIPIAO
 * @date 2021/3/22 23:24
 * <p>
 * 乞丐版服务提供方restfull接口
 */
@ConfigurationProperties(prefix = "server")
@RestController
public class HelloWorldController {

    private String port;


    @GetMapping("server_provide/v1/hello/{param}")
    public String hello(@PathVariable String param) {
        return "hello server - port=" + port + " 我被调用了，接收到参数：" + param;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
