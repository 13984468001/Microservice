package com.lipiao.service.fallback;

import com.lipiao.entity.User;
import com.lipiao.service.UserServiceApi;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 接口熔断降级处理工厂
 * <p>
 * 这里可以对具体业务异常类型进行需要的处理
 * <p>
 * 使用Fallback实现容错，获取不到HTTP请求错误状态码和信息 ，这时就可以使用工厂模式来实现Fallback。二者不能同时使用
 *
 * @author LIPIAO
 * @date 2021/3/24 10:27
 */
@Component
public class UserServiceApiFallBackFactory implements FallbackFactory<UserServiceApi> {
    @Override
    public UserServiceApi create(Throwable throwable) {
        return new UserServiceApi() {
            /**
             * 根据ID获取用户信息
             *
             * @param id 用户ID
             * @return 用户信息
             */
            @Override
            public String getUser(String id) {
                throwable.printStackTrace();
                return "服务降级……";
            }

            /**
             * 演示熔断
             *
             * @return 整除结果
             */
            @Override
            public String getUserToExecution() {
                throwable.printStackTrace();
                Throwable throwableCause = throwable.getCause();
                Throwable cause = throwable.getCause();
                String message = throwable.getMessage();
                Throwable[] suppressed = throwable.getSuppressed();
                return "服务降级……<br/>" + message;
            }

            /**
             * 根据ID获取用户信息
             *
             * @param id
             * @return
             */
            @Override
            public User getUserById(String id) {
                throwable.printStackTrace();
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
                throwable.printStackTrace();
                return new User();
            }

            /**
             * 获取用户状态
             *
             * @return 用户状态
             */
            @Override
            public String getUserAlive() {
                throwable.printStackTrace();
                return "服务降级……";
            }

            /**
             * 获取用户列表
             *
             * @return 用户状态
             */
            @Override
            public List<User> getUsers() {
                throwable.printStackTrace();
                return Arrays.asList(new User());
            }
        };
    }
}
