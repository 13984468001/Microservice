package com.lipiao.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LIPIAO
 * @date 2021/3/22 23:35
 * <p>
 * 乞丐版的服务消费方
 */
@RestController
public class HelloWorldConsumer {

    //注入依赖
    @Bean
    //表示开启负载均衡
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Resource
    EurekaClient eurekaClient;
    @Resource
    RestTemplate restTemplate;
    //通过它随机获取到指定服务进行远程调用，会自动进行负责均衡
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("server_consumer/v1/hello/{param}")
    public String helloV1(@PathVariable String param) {

        if (null == restTemplate) {
            restTemplate = new RestTemplate();
        }

        //获取指定appID的服务列表，这里获取的是服务提供方
        List<InstanceInfo> instanceInfos = eurekaClient.getInstancesByVipAddress("server_provide", false);
        if (null != instanceInfos && instanceInfos.size() > 0) {
            InstanceInfo instanceInfo = instanceInfos.get(0);
            String serverProvideURL = "http://" + instanceInfo.getHostName() + ":" + instanceInfo.getPort();
            String serverProvideURI = "server_provide/v1/hello/" + param;
            //组装好服务提供方URl后，使用restTemplate调用。访问走起：http://localhost:8081/server_consumer/v1/hello/%E6%88%91%E6%98%AF%E6%9C%8D%E5%8A%A1%E6%B6%88%E8%B4%B9%E6%96%B9-%E9%A9%AC%E5%B0%8F%E5%85%AD
            String responseStr = restTemplate.getForObject(serverProvideURL + "/" + serverProvideURI, String.class);
            return responseStr;
        }

        return "hello";
    }

    @GetMapping("server_consumer/v2/hello/{param}")
    public String helloV2(@PathVariable String param) {

        //获取指定appID的服务列表，这里获取的是服务提供方
        ServiceInstance serverProvide = loadBalancerClient.choose("server_provide");
        if (null != serverProvide) {
            String serverProvideURL = "http://" + serverProvide.getHost() + ":" + serverProvide.getPort();
            String serverProvideURI = "server_provide/v1/hello/" + param;
            //组装好服务提供方URl后，使用restTemplate调用。访问走起：http://localhost:8081/server_consumer/v1/hello/%E6%88%91%E6%98%AF%E6%9C%8D%E5%8A%A1%E6%B6%88%E8%B4%B9%E6%96%B9-%E9%A9%AC%E5%B0%8F%E5%85%AD
            String responseStr = restTemplate.getForObject(serverProvideURL + "/" + serverProvideURI, String.class);
            return responseStr;
        }

        return "hello";
    }


}
