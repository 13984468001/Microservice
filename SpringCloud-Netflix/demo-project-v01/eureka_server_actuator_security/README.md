# 工程简介

主要使用Actuator、Security

加入了Spring Boot2.x Actuator监控应用

### Eureka Server、Eureka Client 端配置 Actuator 依赖    

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

### 默认端点

Spring Boot 2.0 的Actuator只暴露了health和info端点，提供的监控信息无法满足我们的需求

在1.x中有n多可供我们监控的节点，官方的回答是为了安全….


### 开启所有端点

application.properties中加入如下配置信息

*代表所有节点都加载

```properties
#开启所有端点
management.endpoints.web.exposure.include=*
```



所有端点都开启后的api列表

```json
{
  "_links": {
    "self": {
      "href": "http://localhost:8085/actuator",
      "templated": false
    },
    "archaius": {
      "href": "http://localhost:8085/actuator/archaius",
      "templated": false
    },
    "beans": {
      "href": "http://localhost:8085/actuator/beans",
      "templated": false
    },
    "caches-cache": {
      "href": "http://localhost:8085/actuator/caches/{cache}",
      "templated": true
    },
    "caches": {
      "href": "http://localhost:8085/actuator/caches",
      "templated": false
    },
    "health": {
      "href": "http://localhost:8085/actuator/health",
      "templated": false
    },
    "health-path": {
      "href": "http://localhost:8085/actuator/health/{*path}",
      "templated": true
    },
    "info": {
      "href": "http://localhost:8085/actuator/info",
      "templated": false
    },
    "conditions": {
      "href": "http://localhost:8085/actuator/conditions",
      "templated": false
    },
    "configprops": {
      "href": "http://localhost:8085/actuator/configprops",
      "templated": false
    },
    "env": {
      "href": "http://localhost:8085/actuator/env",
      "templated": false
    },
    "env-toMatch": {
      "href": "http://localhost:8085/actuator/env/{toMatch}",
      "templated": true
    },
    "loggers": {
      "href": "http://localhost:8085/actuator/loggers",
      "templated": false
    },
    "loggers-name": {
      "href": "http://localhost:8085/actuator/loggers/{name}",
      "templated": true
    },
    "heapdump": {
      "href": "http://localhost:8085/actuator/heapdump",
      "templated": false
    },
    "threaddump": {
      "href": "http://localhost:8085/actuator/threaddump",
      "templated": false
    },
    "metrics": {
      "href": "http://localhost:8085/actuator/metrics",
      "templated": false
    },
    "metrics-requiredMetricName": {
      "href": "http://localhost:8085/actuator/metrics/{requiredMetricName}",
      "templated": true
    },
    "scheduledtasks": {
      "href": "http://localhost:8085/actuator/scheduledtasks",
      "templated": false
    },
    "mappings": {
      "href": "http://localhost:8085/actuator/mappings",
      "templated": false
    },
    "refresh": {
      "href": "http://localhost:8085/actuator/refresh",
      "templated": false
    },
    "features": {
      "href": "http://localhost:8085/actuator/features",
      "templated": false
    },
    "service-registry": {
      "href": "http://localhost:8085/actuator/service-registry",
      "templated": false
    }
  }
}
```

### api端点功能

#### Health

​		会显示系统状态:{"status":"UP"}

###  shutdown 

​		用来关闭节点

Client 开启远程关闭功能

```properties
management.endpoint.shutdown.enabled=true
```

使用Post方式请求端点

```json
{
    "message": "Shutting down, bye..."
}
```

### autoconfig 

​		获取应用的自动化配置报告beans 

​		获取应用上下文中创建的所有Bean 


#### configprops 

​		获取应用中配置的属性信息报告 


#### env 

​		获取应用所有可用的环境属性报告 

#### Mappings

 		获取应用所有Spring Web的控制器映射关系报告

####  info 

​		获取应用自定义的信息 

#### metrics

​		返回应用的各类重要度量指标信息 

**Metrics**

​		节点并没有返回全量信息，我们可以通过不同的**key**去加载我们想要的值

 		metrics/jvm.memory.max


### Threaddump

​		1.x版本中为**dump**

​		返回程序运行中的线程信息 

## Eureka Client 健康检查

​		由于server和client通过心跳保持 服务状态，而只有状态为UP的服务才能被访问。看eureka界面中的status。

​		比如心跳一直正常，服务一直UP，但是此服务DB连不上了，无法正常提供服务。

​		此时，我们需要将 微服务的健康状态也同步到server。只需要启动eureka的健康检查就行。这样微服务就会将自己的健康状态同步到eureka。配置如下即可。

### 开启手动控制

​		在client端配置：将自己真正的健康状态传播到server。

```yaml
eureka:
  client:
    healthcheck:
      enabled: true
```

### 改变Client健康状态的Service

```java
@Service
public class HealthStatusService implements HealthIndicator{

	private Boolean status = true;

	public void setStatus(Boolean status) {
		this.status  = status;
	}

	@Override
	public Health health() {
		// TODO Auto-generated method stub
		if(status)
		return new Health.Builder().up().build();
		return new Health.Builder().down().build();
	}

	public String getStatus() {
		// TODO Auto-generated method stub
		return this.status.toString();
	}
}
```

### 测试用的Controller

```java
@GetMapping("/health")
public String health(@RequestParam("status") Boolean status) {
    healthStatusSrv.setStatus(status);
    return healthStatusSrv.getStatus();
}
```

浏览器请求：http://localhost:8085/health/true 客户端服务手动上线：**UP** (1) - [localhost:eureka_Client_actuator:8085](http://192.168.88.1:8085/actuator/info)

​					 http://localhost:8085/health/false 客户端服务手动下线：**DOWN** (1) -** [localhost:eureka_Client_actuator:8085](http://192.168.88.1:8085/actuator/info)

## 安全配置

### Eureka Client 端配置 security依赖    

```xml
<!-- 安全配置 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

### 开启Eureka Server 安全连接

```properties
# 应用名称
spring.application.name=eurekaServer
# 应用服务 WEB 访问端口
server.port=8761
#注册自己到Eureka_Server，默认true。单节点时本身就是Server，因此设置为false。
#eureka.client.register-with-eureka=false
#是否从Eureka_Server拉去注册信息，默认是true。单节点时本身就是Server，不用拉去。
#eureka.client.fetch-registry=false
#设置服务注册中心URL，用于client与Server交流
eureka.client.service-url.defaultZone=http://pl:pl@eureka2.com:8762/eureka
eureka.instance.appname=${spring.application.name}
#主机名
eureka.instance.hostname=eureka1.com
#互为服务端和客户端，这里也配置
#续约发送间隔默认30秒，心跳间隔
eureka.instance.lease-renewal-interval-in-seconds=5
#表示eureka client间隔多久去拉取服务注册信息，默认为30秒，对于api-gateway，如果要迅速获取服务注册状态，可以缩小该值，比如5秒
eureka.client.registry-fetch-interval-seconds=5
# 续约到期时间（默认90秒）
eureka.instance.lease-expiration-duration-in-seconds=60
#关闭自我保护模式
eureka.server.enable-self-preservation=false
#失效服务间隔
eureka.server.eviction-interval-timer-in-ms=3000
#Spring Boot2.x Actuator监控应用，开启所有端点
management.endpoints.web.exposure.include=*
#开启Eureka安全连接，是默认开启了防止跨域攻击，需要手动关闭——>WebSecurityConfig.class
spring.security.user.name=pl
spring.security.user.password=pl
#```````````````````````````````````````````````````````````````````````````````````````````````````````````````````
# 应用名称
spring.application.name=eurekaServer
# 应用服务 WEB 访问端口
server.port=8762
#注册自己到Eureka_Server，默认true。单节点时本身就是Server，因此设置为false。
#eureka.client.register-with-eureka=false
#是否从Eureka_Server拉去注册信息，默认是true。单节点时本身就是Server，不用拉去。
#eureka.client.fetch-registry=false
#设置服务注册中心URL，用于client与Server交流
eureka.client.service-url.defaultZone=http://pl:pl@eureka1.com:8762/eureka
eureka.instance.appname=${spring.application.name}
#主机名
eureka.instance.hostname=eureka2.com
#互为服务端和客户端，这里也配置
#续约发送间隔默认30秒，心跳间隔
eureka.instance.lease-renewal-interval-in-seconds=5
#表示eureka client间隔多久去拉取服务注册信息，默认为30秒，对于api-gateway，如果要迅速获取服务注册状态，可以缩小该值，比如5秒
eureka.client.registry-fetch-interval-seconds=5
# 续约到期时间（默认90秒）
eureka.instance.lease-expiration-duration-in-seconds=60
#关闭自我保护模式
eureka.server.enable-self-preservation=false
#失效服务间隔
eureka.server.eviction-interval-timer-in-ms=3000
#Spring Boot2.x Actuator监控应用，开启所有端点
management.endpoints.web.exposure.include=*
#开启Eureka安全连接，是默认开启了防止跨域攻击，需要手动关闭——>WebSecurityConfig.class
spring.security.user.name=pl
spring.security.user.password=pl
```

### Eureka Client配置

```properties
# 应用名称
spring.application.name=server_consumer
# 应用服务 WEB 访问端口
server.port=8081
#注册自己到Eureka_Server，默认true。单节点时本身就是Server，因此设置为false。
#eureka.client.register-with-eureka=false
#是否从Eureka_Server拉去注册信息，默认是true。单节点时本身就是Server，不用拉去。
#eureka.client.fetch-registry=false
#设置服务注册中心URL，用于client与Server交流
eureka.client.service-url.defaultZone=http://pl:pl@eureka1.com:8761/eureka,http://pl:pl@eureka2.com:8762/eureka
eureka.instance.appname=${spring.application.name}
#主机名
eureka.instance.hostname=eureka5.com
#续约发送间隔默认30秒，心跳间隔
eureka.instance.lease-renewal-interval-in-seconds=5
#表示eureka client间隔多久去拉取服务注册信息，默认为30秒，对于api-gateway，如果要迅速获取服务注册状态，可以缩小该值，比如5秒
eureka.client.registry-fetch-interval-seconds=5
# 续约到期时间（默认90秒）
eureka.instance.lease-expiration-duration-in-seconds=60
#注册IP地址
#表示将自己的ip注册到EurekaServer上。不配置或false，表示将操作系统的hostname注册到server
eureka.instance.prefer-ip-address=true
#实际能访问到的Ip
eureka.instance.ip-address=192.168.88.1
#Spring Boot2.x Actuator监控应用，开启所有端点
management.endpoints.web.exposure.include=*
#将自身健康状态传播到Eureka_Server端
eureka.client.healthcheck.enabled=true
#开启远程关闭应用功能
management.endpoint.shutdown.enabled=true

```



### 如果服务注册报错

```java
Root name 'timestamp' does not match expected ('instance') for type [simple
```

​		是默认开启了防止跨域攻击

#### 手动关闭

​		在服务端增加配置类

```java
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable();
		super.configure(http);
	}
}
```