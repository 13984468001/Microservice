package com.lipiao.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * 用于feign调用远程服务认证
 *
 * @author LIPIAO
 * @date 2021/3/23 20:58
 */
public class CustomRequestAuthInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // cm9vdDpyb290？哪来的呢？
        requestTemplate.header("Authorization", "Basic cm9vdDpyb290");
    }
}
