package com.lipiao.controller;

import com.lipiao.entity.User;
import com.lipiao.service.RestTemplateHystrixService;
import com.lipiao.service.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author LIPIAO
 * @date 2021/3/23 17:40
 */
@RestController
public class UserController {

    @Autowired
    private UserServiceApi userServiceApi;
    @Autowired
    private RestTemplateHystrixService restTemplateHystrixService;

    /**
     * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^公共接口方法
     */
    @GetMapping(value = "/getUserById/{id}")
    public User getUserById(@PathVariable String id) {
        return this.userServiceApi.getUserById(id);
    }

    @GetMapping(value = "/getUserAlive")
    public String getUserAlive() {
        return this.userServiceApi.getUserAlive();
    }


    @GetMapping(value = "/getUsers")
    public List<User> getUser() {
        return this.userServiceApi.getUsers();
    }

    @GetMapping("/getUserByIdIsPost/{id}")
    public User getUserByIdIsPost(@PathVariable String id) {
        return this.userServiceApi.getUserByIdIsPost(new User(id, null, 0));
    }

    /**
     * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^服务提供方独有方法
     */
    @GetMapping(value = "/getUser/{id}")
    public String getUser(@PathVariable String id) {
        String res = this.userServiceApi.getUser(id);
        return res;
    }

    /**
     * 使用map接收Get请求参数
     *
     * @param map
     * @return
     */
    @GetMapping(value = "/getUserToMap")
    public String getUser(@RequestParam Map<String, String> map) {
        String res = this.userServiceApi.getUser(map.get("id"));
        return res;
    }

    /**
     * 演示熔断
     *
     * @return 整除结果
     */
    @GetMapping(value = "/getUserToExecution")
    public String getUserToExecution() {
        return this.userServiceApi.getUserToExecution();
    }

    /**
     * 演示 RestTemplate熔断降级
     *
     * @return
     */
    @GetMapping("/demoRT")
    public String demoRt() {
        return this.restTemplateHystrixService.demoRT();
    }

}
