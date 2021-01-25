# Spring 事务处理:droplet:

### 事务

一系列**数据库操作**的集合，必须同时成功或失败

**事务处理**，**spring**放在**service**的事务方法上



### 事务隔离级别:door:

定义在接口**TransactionDefinition**中，是**常量**

[PlatformTransactionManager](https://docs.spring.io/spring-framework/docs/current/javadoc-api/)

[TransactionDefinition](https://docs.spring.io/spring-framework/docs/current/javadoc-api/)

| Modifier and Type | Field and Description                                        |
| :---------------- | :----------------------------------------------------------- |
| `static int`      | `ISOLATION_DEFAULT`Use the default isolation level of the underlying datastore. |
| `static int`      | `ISOLATION_READ_COMMITTED`Indicates that dirty reads are prevented; non-repeatable reads and phantom reads can occur. |
| `static int`      | `ISOLATION_READ_UNCOMMITTED`Indicates that dirty reads, non-repeatable reads and phantom reads can occur. |
| `static int`      | `ISOLATION_REPEATABLE_READ`Indicates that dirty reads and non-repeatable reads are prevented; phantom reads can occur. |
| `static int`      | `ISOLATION_SERIALIZABLE`Indicates that dirty reads, non-repeatable reads and phantom reads are prevented. |

* `ISOLATION_DEFAULT`：默认的隔离级别，**MySQL**是`ISOLATION_REPEATABLE_READ`，**Oracle**是`ISOLATION_READ_COMMITTED`；
* `ISOLATION_READ_UNCOMMITTED`：读未提交，未解决任何并发问题；
* `ISOLATION_READ_COMMITTED`：读已提交，解决**读脏数据**，存在**不可重复读**以及**幻读**问题；
* `ISOLATION_REPEATABLE_READ`：可重复读，解决**读脏数据**、**不可重复读**，存在**幻读**；
* `ISOLATION_SERIALIZABLE`：**串行化**，不存在并发问题；



### 事务超时:timer_clock:

表示一个方法最长执行时间，如果超过，**回滚**

`TIMEOUT_DEFAULT`



### 事务的传播行为:signal_strength:

| Modifier and Type | Field and Description                                        |
| :---------------- | :----------------------------------------------------------- |
| `static int`      | `PROPAGATION_MANDATORY`Support a current transaction; throw an exception if no current transaction exists. |
| `static int`      | `PROPAGATION_NESTED`Execute within a nested transaction if a current transaction exists, behave like [`PROPAGATION_REQUIRED`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/TransactionDefinition.html#PROPAGATION_REQUIRED) otherwise. |
| `static int`      | `PROPAGATION_NEVER`Do not support a current transaction; throw an exception if a current transaction exists. |
| `static int`      | `PROPAGATION_NOT_SUPPORTED`Do not support a current transaction; rather always execute non-transactionally. |
| `static int`      | `PROPAGATION_REQUIRED`Support a current transaction; create a new one if none exists. |
| `static int`      | `PROPAGATION_REQUIRES_NEW`Create a new transaction, suspending the current transaction if one exists. |
| `static int`      | `PROPAGATION_SUPPORTS`Support a current transaction; execute non-transactionally if none exists. |

* `PROPAGATION_REQUIRED`：**默认**，方法必须在事务中执行，若当前存在事务，就加入到事务；否则，创建新事务；
* `PROPAGATION_REQUIRES_NEW`：总是新建一个事务，若存在事务，将该事务挂起；
* `PROPAGATION_SUPPORTS`：若没有事务，则以非事务方式执行；



### 提交事务，回滚事务时机

* 当业务方法**执行成功**，没有**异常抛出**，方法执行完毕，**spring**自动提交事务；
* 当业务方法**抛出运行时异常**，**spring**执行回滚，调用事务管理器的**rollback**；
* 当业务方法**抛出非运行时异常**，主要是**受查异常**时，提交事务；



### spring 事务处理过程

* 指定要使用的事务管理实现类，用**\<bean\>**；
* 指定哪些类、哪些方法需要加入事务管理功能；
* 指定方法需要的**隔离级别**、**传播行为**、**超时**；



### spring通过AOP添加事务

**@Transactional**，放在public方法上面，表示该方法有事务

* **propagation**：传播属性；
* **isolation**：隔离属性；
* **readOnly**：是否是只读，默认false；
* **timeout**：超时；
* **rollbackFor**：异常类，抛出该异常类要回滚；
* **rollbackForClassName**：异常类名；
* **noRollbackFor**：不需要回滚；
* **noRollbackForClassName**：不需要回滚；

**使用步骤**：

1. 声明**事务管理器**对象

    ~~~xml
        <!-- 配置事务管理器 -->
        <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
            <!-- 连接的数据库 -->
            <property name="dataSource" ref="myDataSource"/>
        </bean>
    ~~~

2. 开启事务的注解驱动

    ~~~xml
        <!-- 开启事务注解驱动 -->
        <tx:annotation-driven transaction-manager="transactionManager"/>
    ~~~

    

3. 在方法上面加注解

    ~~~java
        @Transactional(
                    propagation = Propagation.REQUIRED,
                    isolation = Isolation.DEFAULT,
                    readOnly = false,
                    rollbackFor = {
                            NullPointerException.class,
                            NotEnoughException.class
                    }
            )
        @Override
        public void buy(Integer id, Integer nums) {
            System.out.println("===== buy 方法开始=====");
            // 记录销售信息
            Sale sale = new Sale();
            sale.setGid(id);
            sale.setNums(nums);
            System.out.println(saleDao.getClass());
            saleDao.insertSale(sale);
            // 更新库存
            Goods goods = goodsDao.selectGoods(id);
            if(goods == null){
                throw new NullPointerException("编号是：" + id + ",商品不存在");
            }else if(goods.getAmount() < nums){
                throw new NotEnoughException("编号是：" + id + "，商品库存不足");
            }
            Goods buyGoods = new Goods();
            buyGoods.setId(id);
            buyGoods.setAmount(nums);
            goodsDao.updateGoods(buyGoods);
            System.out.println("===== buy 方法完成=====");
        }
    ~~~

    

    

### Error

* **mysql**驱动版本，本地MySQL版本**8.0.22**，驱动使用**5.1.6**出现下面问题：

    ![error](/.md/p1.png)

    将驱动版本设为**8.0.22**，解决该问题

    