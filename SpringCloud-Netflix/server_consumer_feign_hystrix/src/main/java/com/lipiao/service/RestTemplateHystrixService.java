package com.lipiao.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author LIPIAO
 * @date 2021/3/24 12:31
 * <p>
 * RestTemplate整合Hystrix
 * <p>
 * 有个疑问？
 * <p>
 * RestTemplate远程调用feign，feign Server 需要认证怎么办？
 */
@Service
public class RestTemplateHystrixService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 整合Hystrix
     * <p>
     * defaultFallback：默认的后备方法不能有参数，返回类型需与业务接口方法返回类型兼容
     * <p>
     * fallbackMethod：指定一种处理后备逻辑的方法。 后备方法应该在HystrixCommand所在的类中定义。 备用方法还应与作为hystrix命令调用的方法具有相同的签名。
     *
     * @return
     */
    @HystrixCommand(/*defaultFallback = "demoREFallback", */fallbackMethod = "demoREFallbackMethod")
    public String demoRT() {
        String url = "http://serverProvideFeign/getUserToExecution";
        String resStr = restTemplate.getForObject(url, String.class);
        return resStr;
    }

    /**
     * RestTemplate 熔断降级业务处理方法
     *
     * @return
     */
    public String demoREFallback() {
        return "服务降级……";
    }

    public String demoREFallbackMethod(Throwable throwable) {
        throwable.printStackTrace();
        return "服务降级……";
    }

}
