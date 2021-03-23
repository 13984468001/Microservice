package com.lipiao.service;

import com.lipiao.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LIPIAO
 * @date 2021/3/23 16:37
 * <p>
 * 用户公共接口
 */
@RequestMapping("/users")
public interface UserService {

    /**
     * 根据ID获取用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getUserById")
    User getUserById(@RequestParam("id") String id);

    /**
     * 根据ID获取用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("/getUserByIdIsPost")
    User getUserByIdIsPost(@RequestBody User user);

    /**
     * 获取用户状态
     *
     * @return 用户状态
     */
    @GetMapping("/getUserAlive")
    String getUserAlive();

    /**
     * 获取用户列表
     *
     * @return 用户状态
     */
    @PostMapping("/getUsers")
    List<User> getUsers();

}
