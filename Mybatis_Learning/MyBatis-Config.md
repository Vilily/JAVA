# MyBatis主配置文件:wrench:

### 主配置文件内容:page_with_curl:

~~~xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>

    <typeAliases>
        <package name="com.baowj.domain"/>
    </typeAliases>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/springdb?serverTimezone=UTC"/>
                <property name="username" value="root"/>
                <property name="password" value="bwj678"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="com/baowj/dao/StudentDao.xml"/>
    </mappers>
</configuration>
~~~



### \<transactionManager type="JDBC"/\>

**MyBatis**提交事物，回滚事务的方式

1. **JDBC**：底层调用**JDBC**处理；
2. **MANAGED**：把事物处理委托给其他容器；



### \<dataSource type="POOLED"\>

表示数据源，在**JAVA**中，实现了**java.sql.DataSource**的接口都是数据源

1. **POOLED**：使用**连接池**；
2. **UPOOLED**：不使用**连接池**；每次执行**sql**语句，要先创建**连接**，再执行sql；
3. **JNDI**：**java**命名和目录服务；



## 属性配置文件

把**数据库连接信息**放到一个单独的文件中，和**mybatis主配置文件**分开

1. 在**resources**目录创建**属性配置文件**，**xxxxxx.properties**；

    *resources/jdbc.properties*

    ~~~xml
    jdbc.driver=com.mysql.cj.jdbc.Drive
    jdbc.url=jdbc:mysql://localhost:3306/springdb?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=true
    jdbc.user=root
    jdbc.password=bwj678
    ~~~

    

2. 在mybatis**主配置文件**，使用**\<property\>**指定文件位置；

    ~~~xml
    <properties resource="jdbc.properties"/>
    
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driver}"/>
                <property name="url" value="${jdbc.url}"/>
                <property name="username" value="${jdbc.user}"/>
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
    </environments>
    ~~~


## 别名

类型别名可为 Java 类型设置一个缩写名字。它仅用于 **XML** 配置，意在降低冗余的全限定类名书写。例如：

~~~xml
<typeAliases>
  <typeAlias alias="Author" type="domain.blog.Author"/>
  <typeAlias alias="Blog" type="domain.blog.Blog"/>
  <typeAlias alias="Comment" type="domain.blog.Comment"/>
  <typeAlias alias="Post" type="domain.blog.Post"/>
  <typeAlias alias="Section" type="domain.blog.Section"/>
  <typeAlias alias="Tag" type="domain.blog.Tag"/>
</typeAliases>
~~~



