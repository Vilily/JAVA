# Spring + mybatis:fire:

### 简介:sun_with_face:

**IOC**能把**MyBatis**和**Spring**集成在一起，变成“一个框架”，因为**IOC**能创建对象，可以把**MyBatis**框架中的对象交给**Spring**统一管理

### 步骤:star2:

1. 新建**maven**项目；
2. 加入**maven**依赖：
   * **spring**依赖；
   * **mybatis**依赖；
   * **mysql**驱动；
   * **spring**事物依赖；
   * **mybatis**和**spring**集成依赖；
3. 创建**实体类**；
4. 创建**dao**接口和**mapper**文件；
5. 创建**mybatis**主配置文件；
6. 创建**Service**接口和实现类；
7. 创建**spring**的配置文件；
8. 创建**测试类**；



### Bean:bee:

~~~xml
    <context:property-placeholder location="classpath:jdbc.properties"/>
    <!--声明数据源DataSource,作用是连接数据库-->
    <bean id="myDataSource" class="com.alibaba.druid.pool.DruidDataSource"
          init-method="init" destroy-method="close">
        <!-- set注入，给DruidDataSource提供连接数据库信息-->
        <property name="url" value="${jdbc.url}" /> <!-- setUrl() -->
        <property name="username" value="${jdbc.name}"/>
        <property name="password" value="${jdbc.pwd}"/>
        <property name="maxActive" value="${jdbc.active}"/>
    </bean>

    <!-- 声明mybatis中提供的SqlSessionFactoryBean类 -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <!-- set注入，把数据库连接池给datasource属性 -->
        <property name="dataSource" ref="myDataSource"/>
        <!-- mybatis主配置文件位置 -->
        <property name="configLocation" value="classpath:mybatis.xml"/>
    </bean>

    <!-- 创建dao对象，使用SqlSession的getMapper
        MapperScannerConfigurer:在内部调用getMapper()生成每个dao接口的代理对象。
    -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!-- 指定SqlSessionFactory对象的id -->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <!-- 指定包名，dao接口所在包名
            MapperScannerConfigurer会扫描每个接口，把每个接口都执行，得到每个接口的对象
        -->
        <property name="basePackage" value="com.baowj.dao"/>
    </bean>

    <bean id="studentService" class="com.baowj.service.impl.StudentServiceImpl">
        <property name="studentDao" ref="studentDao"/>
    </bean>
~~~



### Druid连接池:dragon:

[Druid官方文档](https://github.com/alibaba/druid/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98)



### 补充

**Spring**整合**MyBatis**自动提交事物



### 踩坑经验:sob:

**mapper**文件**namespace**

~~~xml
<mapper namespace="com.baowj.dao.StudentDao">
~~~

**不能简写！！！！！！！！！！！！！！！！！！**

~~~xml
<mapper namespace="StudentDao">
~~~

