package com.lipiao.service.fallback;

import com.lipiao.entity.User;
import com.lipiao.service.UserServiceApi;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 接口熔断降级处理
 * <p>
 * 在网络请求时，可能会出现异常请求，如果还想再异常情况下使系统可用，那么就需要容错处理。比如:网络请求超时时给用户提示“稍后重试”或使用本地快照数据等等。
 * <p>
 * 这里可以对具体业务异常类型进行需要的处理
 *
 * @author LIPIAO
 * @date 2021/3/24 10:27
 */
@Component
public class UserServiceApiFallBack implements UserServiceApi {
    /**
     * 根据ID获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @Override
    public String getUser(String id) {
        return "服务降级……";
    }

    /**
     * 演示熔断
     *
     * @return 整除结果
     */
    @Override
    public String getUserToExecution() {
        return "服务降级……";
    }

    /**
     * 根据ID获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(String id) {
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
        return new User();
    }

    /**
     * 获取用户状态
     *
     * @return 用户状态
     */
    @Override
    public String getUserAlive() {
        return "服务降级……";
    }

    /**
     * 获取用户列表
     *
     * @return 用户状态
     */
    @Override
    public List<User> getUsers() {
        return Arrays.asList(new User());
    }
}
