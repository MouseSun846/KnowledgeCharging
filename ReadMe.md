## SpringBoot集成MyBatis

### 1、引入pom.xml文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.6.5</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.mouse</groupId>
    <artifactId>ips</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>ips</name>
    <description>Demo project for Spring Boot</description>
    <properties>
        <java.version>1.8</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--mybatis的依赖-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.2.2</version>
        </dependency>
        <!--数据库驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.25</version>
        </dependency>
        <!--数据库连接池-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.2.4</version>
        </dependency>
        <!--分页组件-->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>1.4.1</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>

```

### 2、配置application.yaml

```xml

server:
  port: 8080
spring:
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    username: root
    password: 123456
    url: jdbc:mysql://localhost:3306/ips?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT
    driver-class-name: com.mysql.cj.jdbc.Driver
mybatis:
  type-aliases-package: com.mouse.ips.pojo  # 别名
  mapper-locations: classpath:/mapper/*.xml # xml映射文件
logging:
  file:
    name: log/log.log
  level:
    root: info
    com: debug

```

### 3、创建User对象

```java
package com.mouse.ips.pojo;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String pwd;
    private String perm;
}

```

### 4、创建UserMapper.xml，

对应目录为resources路径下 mapper/UserMapper.xml，因为已经再application.yaml指定了对应路径

### 5、编写UserDao

```java
package com.mouse.ips.dao;

import com.mouse.ips.pojo.User;
import com.mouse.ips.pojo.query.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // 告诉springboot这是mybatis的一个类
@Repository // UserDao由Spring boot的容器管理
public interface UserDao {
    // 查询所有用户
     public List<User> listUser();
     // 根据用户名分页展示
     public List<User> listUserByName(UserQuery userQuery);

}

```

### 6、编写Services

```java
package com.mouse.ips.services;

import com.github.pagehelper.PageInfo;
import com.mouse.ips.pojo.User;
import com.mouse.ips.pojo.query.UserQuery;

import java.util.List;

public interface UserServices {
    // 查询所有用户
    public List<User> listUser();
    // 根据用户名分页展示
    public PageInfo<User> listUserByName(UserQuery userQuery);
}

```

### 7、junit进行测试

```java
package com.mouse.ips.services.impl;

import com.mouse.ips.pojo.User;
import com.mouse.ips.services.UserServices;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
class UserServicesImplTest {
    @Autowired
    private UserServices userServices;

    @Test
    void listUser() {
        List<User> users = userServices.listUser();
        System.out.println(users);
    }

    @Test
    void listUserByName() {
    }
}
```

### 8、编写Controller

```java
package com.mouse.ips.controller;

import com.mouse.ips.pojo.User;
import com.mouse.ips.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;
    @GetMapping("/list")
    public List<User> listUser()
    {
        return userServices.listUser();
    }
}

```

### 9、接口测试

```http
###
GET http://localhost:8080/user/list

<> 2022-04-03T050338.200.json
```



## 知识点集合

1、注解

```java
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor // 所有的无参构造器
@AllArgsConstructor // 所有的有参构造器

import org.apache.ibatis.annotations.Mapper;
@Mapper
这个注解用于DAO层，表示用于Mybatis

```



## 参考资料

[PageHelpDoc](https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md)

# FAQ

## 1.启动报错
```
***************************
APPLICATION FAILED TO START
***************************

Description:

The dependencies of some of the beans in the application context form a cycle:

┌──->──┐
|  com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration
└──<-──┘


Action:

Relying upon circular references is discouraged and they are prohibited by default. Update your application to remove the dependency cycle between beans. As a last resort, it may be possible to break the cycle automatically by setting spring.main.allow-circular-references to true.
```
解决办法：
pagehelper之前用的1.3.0版本会有循环依赖问题，springboot2.6好像禁止循环依赖还是啥的，翻来翻去没看到解决办法，后面去pageHelper github看，才看到最新版本已经解决了这个问题。
解决方案：更新到最新版本pageHelper，1.4.1已经解决
```xml
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.4.1</version>
</dependency>
```

