# MyBatis学习:rocket:



[官方文档](https://mybatis.org/mybatis-3/zh/getting-started.html)

* [MyBatis 新建项目](ch01-hello-mybatis):baby:
* [MyBatis dao接口使用](ch02-mybatis-dao):yum:
* [MyBatis 动态代理](ch03-proxy-dao):man:
* [MyBatis 传参](ch04-param):older_man:
* [MyBatis 输出结果](ch05-result):man_artist:
* [MyBatis 主配置文件](MyBatis-Config.md):man_astronaut:
* [MyBatis-注解使用](ch07-annotation)

## MyBatis加载过程

* **Resources**获取全局**配置文件**；

* 实例化**SqlSessionFactoryBuilder**构造器；

    * 解析配置文件流；

* 实例化**SqlSessionFactory**；

    
