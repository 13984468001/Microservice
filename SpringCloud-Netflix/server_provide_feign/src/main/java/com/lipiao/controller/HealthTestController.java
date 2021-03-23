package com.lipiao.controller;

import com.lipiao.service.HealthStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LIPIAO
 * @date 2021/3/23 14:52
 * <p>
 * 手工控制Eureka_Client健康状态
 */
@RestController
public class HealthTestController {

    @Autowired
    private HealthStatusService healthStatusService;

    @GetMapping("/health/{status}")
    public String health(@PathVariable Boolean status) {
        healthStatusService.setStatus(status);
        return healthStatusService.getStatus();
    }
}
