package com.lipiao.controller;

import com.lipiao.entity.User;
import com.lipiao.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


/**
 * @author LIPIAO
 * @date 2021/3/23 17:11
 */
@RestController
public class UserController implements UserService {
    /**
     * 用于显示负载均衡情况
     */
    @Value("${server.port}")
    String port;

    /**
     * 根据ID获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(String id) {
        try {
            //演示ribbon重试机制
            //Thread.sleep(4000);
            System.out.println("当前服务端口：" + port);
            if (StringUtils.isNotBlank(id)) {
                return new User(id, "马小六 PORT=" + port, 18);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new User();
    }

    /**
     * 根据ID获取用户信息
     *
     * @param user
     * @return
     */
    @Override
    public User getUserByIdIsPost(User user) {
        System.out.println("当前服务端口：" + port);
        if (null != user && StringUtils.isNotBlank(user.getId())) {
            return new User(user.getId(), "马小六 PORT=" + port, 18);
        }
        return new User();
    }

    /**
     * 获取用户状态
     *
     * @return 用户状态
     */
    @Override
    public String getUserAlive() {
        String str = "当前服务端口：" + port;
        System.out.println(str);
        return "睡觉中……………… " + str;
    }

    /**
     * 获取用户列表
     *
     * @return 用户状态
     */
    @Override
    public List<User> getUsers() {
        return Arrays.asList(new User("99", "马小六  PORT=" + port, 18));
    }

    /**
     * 获取用户信息
     *
     * @return 返回用户信息
     */
    @GetMapping("/getUser")
    public String getUser(@RequestParam("id") String id) {
        String str = "当前服务端口：" + port;
        System.out.println(str);
        return new User(id, "马小六 PORT=" + port, 18).toString();
    }


}
