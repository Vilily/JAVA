# Aspect框架

### 基本概念：

1. 切面执行时间，在规范中叫**Advice**；

   **用注解表示**：

   * **@Before**

   * **@AfterReturning**
   * **@Around**
   * **@AfterThrowing**
   * **@After**

2. 切面执行的位置，使用**切入点表达式**：

   ~~~java
   execution(modifiers-pattern?
             ret-type-pattern
             declaring-type-pattern?name-pattern(param-pattern)
             throws-pattern?)
   ~~~

   * **modifiers-pattern**：访问权限类型；
   * **ret-type-pattern**：返回值类型；
   * **declaring-type-pattern**：包名类名；
   * **name-pattern(param-pattern)**：方法名
   * **throws-pattern**：抛出异常类型

   **表达式符号**：

   | 符号 | 功能                                                         |
   | ---- | ------------------------------------------------------------ |
   | *    | 0至多个任意字符                                              |
   | ..   | 用在方法参数中：表示任意多个参数；用在包名后：表示当前包和其子包。 |
   | +    | 用在类名后：表示当前类及其子类；用在接口后：表示当前接口及其实现类。 |

   

   **例子**：

   ~~~java
   execution(public * *(..)) //任意公共方法
   
   execution(* set* (..)) //任意一个以set开头的方法
   
   execution(* com.xyz.service.*.* (..)) //sevice包里的任意类的任意方法
   
   execution(* com.xyz.service..*.* (..)) //service包或者子包里的任意类的任意方法
       
   execution(* *..service.*.* (''))// 所有包下的sevice包里的任意类的任意方法
   ~~~

   

## 使用AspectJ实现AOP：

### 基本步骤：

* 新建maven项目
* 加入依赖
  * **spring**依赖
  * **aspectj**依赖
  * **junit**单元测试
* 创建目标类
* 创建切面类：普通类
  * 在类上加入注解**@Aspect**
  * 在类中定义方法：切面执行的功能方法
  * 在方法上面加入*通知注解*：**@Before**....
  * 指定*切入点表达式*：**execution()**
* 创建spring配置文件：声明对象，把对象交给容器统一管理；
  * 声明*目标对象*
  * 声明*切面类对象*
  * 声明aspectj框架的*自动代理生成器标签*
    * **自动代理生成器**：完成代理对象的自动创建
* 创建测试类



### 注解：

#### @Aspect

**功能**：表示当前类是切面类；

**来源**：*AspectJ*的注解；

**位置**：在类定义上面。

~~~java
@Aspect
public class MyAspect {
}
~~~



#### @Before

**功能**：前置通知，在目标方法之前执行；

**属性**：

* **value**：切入点表达式，表示切面的功能执行位置；

**位置**：方法上面。

~~~java
@Before(value = "execution(public void com.baowj.study01.SomeServiceImpl.doSome(String, Integer))")
public void myBefore(){
    // 功能
~~~

