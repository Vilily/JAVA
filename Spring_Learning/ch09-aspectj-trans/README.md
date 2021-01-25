# Spring 事务处理:droplet:-AspectJ

### 实现步骤

1. 加入**aspectj**依赖：

    ~~~xml
        <!-- aspectJ依赖 -->
        <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-aspects</artifactId>
          <version>5.2.5.RELEASE</version>
        </dependency>
    ~~~

2. 声明**事务管理器**对象

    ~~~xml
        <!-- 配置事务管理器 -->
        <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
            <!-- 连接的数据库 -->
            <property name="dataSource" ref="myDataSource"/>
        </bean>
    ~~~

3. 声明方法需要的事务类型（**隔离级别**、**传播行为**、**超时**）

    ~~~xml
        <!-- 声明业务方法的事务属性 -->
        <!--
            id: advice名称
            transaction-manager: 声明的事务管理器名称
        -->
        <tx:advice id="myAdvice" transaction-manager="transactionManager">
            <!-- 配置事务属性 -->
            <tx:attributes>
                <!-- name不带包名，只是方法名/或者统配符 -->
                <tx:method name="buy"
                           propagation="REQUIRED"
                           isolation="DEFAULT"
                           rollback-for="java.lang.NullPointerException, com.baowj.excep.NotEnoughException"/>
            </tx:attributes>
        </tx:advice>
        
        <!-- 配置aop -->
        <aop:config>
            <!-- 配置切入点表达式：指定哪些包中类要使用事务
                id: 切入点表达式的名称
                expression: 切入点表达式
            -->
            <aop:pointcut id="servicePt" expression="execution(* *..service..*.*(..))"/>
            
            <!-- 配置增强器，关联advice和pointcut -->
            <aop:advisor advice-ref="myAdvice" pointcut-ref="servicePt"/>
        </aop:config>
    ~~~
   
4. 配置**aop**，指定哪些类要创建代理