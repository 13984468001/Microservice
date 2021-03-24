package com.lipiao.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

/**
 * @author LIPIAO
 * @date 2021/3/23 14:53
 * <p>
 * 手工控制Eureka_Client健康状态
 */
@Service
public class HealthStatusService implements HealthIndicator {

    private Boolean status = true;

    /**
     * 当发现服务抛异常或者其他情况导致服务不可用时，Eureka还未踢出该服务，可将status设置为false来手动将该服务下线
     *
     * @param status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public Health health() {
        // TODO Auto-generated method stub
        if (status) {
            return new Health.Builder().up().build();
        }
        return new Health.Builder().down().build();
    }

    public String getStatus() {
        // TODO Auto-generated method stub
        return this.status.toString();
    }
}