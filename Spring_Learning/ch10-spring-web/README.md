# spring Web

### 实现步骤

1. 创建maven-web项目
2. 加入依赖**spring**、**mybatis**、**jsp**、**servlet**
3. 创建代码文件



### 创建监听器

**web.xml**

~~~xml
    <!-- 注册监听器
        创建对象后会读取 /WEB-INF/applicationContext.xml文件
        修改文件位置
    -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
    </context-param>

    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
~~~

