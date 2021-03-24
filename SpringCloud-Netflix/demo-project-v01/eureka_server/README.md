# 工程简介

本地部署Eureka高可用集群

使用IDEA创建springboot项目(阿里的：https://start.aliyun.com)，选择依赖

![image-20210322225643796](attac\image-20210322225643796.png)

![image-20210322225727092](attac\image-20210322225727092.png)

创建成功后启动类加注解：@EnableEurekaServer

## 配置

1. 在hosts文件中配置
   
    127.0.0.1 eureka1.com
    
    127.0.0.1 eureka2.com
    
2. 在IDEA的Edit Configurations里边的配置两个Application 且 VM options中分别配置

    spring.profiles.active=eureka1
    
    spring.profiles.active=eureka2
    
    如如：
    
    ![image-20210322225828969](attac\image-20210322225828969.png)
    
    启动后如图：
    
    ![image-20210322231302712](attac\image-20210322231302712.png)
    
    浏览器访问：eureka1.com:8461
    
    ![image-20210322231438697](attac\image-20210322231438697.png)
    
3. 配置

    application-eureka1.properties

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
    eureka.client.service-url.defaultZone=http://eureka2.com:8762/eureka
    eureka.instance.appname=${spring.application.name}
    #主机名
    eureka.instance.hostname=eureka1.com
    ```

    application-eureka2.properties

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
    eureka.client.service-url.defaultZone=http://eureka2.com:8762/eureka
    eureka.instance.appname=${spring.application.name}
    #主机名
    eureka.instance.hostname=eureka2.com
    ```

    

# 延伸阅读
