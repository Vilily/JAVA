# Spring注解的用法

### 步骤

1. **添加依赖**

   ![yilai](./readmePic/1.png)

2. **添加注解**

3. 声明**组件扫描器**：(会扫描base-package指定的**包**，把**包**和**子包**中的所有类的**注解**，按照其功能创建对象并赋值)

4. ![saomq](./readmePic/2.png)

5. 使用**注解创建对象**

### 详解

#### @Component

**使用方式**：

* 在**实体类**的上面添加

* **value**：指定对象名称（可以省略，默认为类名首字母小写），相当于**\<bean\>**的**id**

~~~java
@Component(value = "myStudent") 
@Component("myStudent1")
@Component
public class Student {
}
~~~

**功能**：创建**spring对象**，等于**\<bean\>**功能

~~~java
@Component(value = "myStudent") <=> <bean id="myStudent" calss="com.baowj.study01.Student" />
~~~

**注意事项**：

*applicationContext.xml*要添加：

~~~java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop-4.0.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">
~~~



#### @Repository

**功能**：创建*dao*对象，用来访问数据库



#### @Service

**功能**：创建*Service*对象，处理业务逻辑



#### @Controller

**功能**：创建*控制器*对象，接受请求，显示处理结果



#### @Value

**使用方式**：

* 在实体类的**属性**上面添加
* 无需*set*方法

**功能**：给**简单属性**赋值

**实例**：

~~~java
@Component(value = "myStudent")
public class Student {

    @Value("李四")
    private String name;
~~~



#### @Autowired

**功能**：spring框架提供的注解，给**引用类型**赋值（*byName，byType*），默认使用*byType*

**使用方式**：

**byType**

* 在属性定义上面，无需set方法
* 在set方法上面

**byName**

* 在属性上面再加上@Qualifier(value="bean的id")【无先后顺序】

**required**

* required=true：如果引用类型赋值**失败**，**报错**
* required=false：如果**失败**，返回**null**

**实例**：

~~~java
// byType
@Component(value = "myStudent")
public class Student {

    @Autowired
    private School mySchool;
    

// byName
@Component(value = "myStudent")
public class Student {

    @Autowired
    @Qualifier(value = "mySchool")
    private School mySchool;
~~~



#### @Resource

**功能**：JDK的注解，给**引用类型**赋值，先使用*byName*，再用*byType*

**使用方式**：

**byType**

* 在属性定义上面，无需set方法
* 在set方法上面

**byName**

* 在属性上面再加上(name="bean的id")

**实例**：

~~~java
// byType
@Component(value = "myStudent")
public class Student {

    @Resource
    private School mySchool;
    
// byName    
@Component(value = "myStudent")
public class Student {

    @Resource(name = "mySchool")
    private School mySchool;
~~~

