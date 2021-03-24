package com.lipiao.service;

import com.lipiao.config.FeignAuthConfiguration;
import com.lipiao.service.fallback.UserServiceApiFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author LIPIAO
 * @date 2021/3/23 17:48
 * <p>
 * openFeign；一般一个服务提供者，写一个interface
 * 此处由于结合了eureka，所以name是 虚拟主机名，默认服务名，请求时 会将它解析成注册表中的服务。
 * 不结合eureka，就是自定义一个client名字。就用url属性指定 服务器列表。url=“http://ip:port/”
 * 此时的name作用就是创建负载均衡器。
 * 也可以添加@RequestMapping
 * <p>
 * fallback fallbackFactory  不可以同时使用。fallback方式，获取不到HTTP请求错误状态码和信息 ，这时就可以使用工厂模式来实现Fallback
 */
@FeignClient(name = "serverProvideFeign", /*fallback = UserServiceApiFallBack.class, */fallbackFactory = UserServiceApiFallBackFactory.class, configuration = FeignAuthConfiguration.class)
public interface UserServiceApi extends UserService {

    /**
     * 根据ID获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @GetMapping(value = "/getUser")
    String getUser(@RequestParam("id") String id);

    /**
     * 演示熔断
     *
     * @return 整除结果
     */
    @GetMapping("/getUserToExecution")
    String getUserToExecution();
}