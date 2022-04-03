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
```
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.4.1</version>
</dependency>
```
