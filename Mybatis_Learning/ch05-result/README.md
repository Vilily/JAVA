# MyBatis 输出:outbox_tray:

**MyBatis**执行**SQL**语句得到**JAVA**对象。

### resultType:tada:

* **自定义类**

    1. **MyBatis**执行**SQL**语句；
    2. **MyBatis**调用类的**无参构造函数**，创建对象；
    3. **MyBatis**把**ResultSet**指定列值赋给**同名**属性；

* **简单类型**

    **resultType**设为简单类型；

    **xml**：

    ~~~xml
    <select id="selectCount" resultType="int">
        select count(*) from student
    </select>
    ~~~

    **dao**：

    ~~~java
    int selectCount();
    ~~~



### 定义别名:name_badge:

1. **typeAlias**

    ~~~xml
        <typeAliases>
            <!--
                type: 自定义别名的全限定名称
                alias: 别名
            -->
            <typeAlias type="com.baowj.domain.Student" alias="stu"/>
        </typeAliases>
    ~~~

2. **package**：name是包名，包中的所有类，类名就是别名。

    ~~~xml
    <typeAliases>
        <package name="com.baowj.domain"/>
    </typeAliases>
    ~~~



### 返回Map

* **key**：列名；
* **value**：列值；
* 最多只能返回一行结果；

**xml**：

~~~xml
<select id="selectMapById" resultType="map">
    select id, name from student where id = #{id};
</select>
~~~

**dao**：

~~~java
Map<Object, Object> selectMapById(Integer id);
~~~



### resultMap

**结果映射**：指定列名和java对象的属性对应关系；

* 自定义**列值**赋给哪个**属性**；
* **列名**和**属性名**不一样时，可以用；

**xml**：

~~~xml
    <!--
        id: 自定义名称；
        type: java类型的全限定名称；
    -->
    <resultMap id="studentMap" type="com.baowj.domain.Student">
        <!-- 主键，用id
            column: 列名;
            property: java属性名;
        -->
        <id column="id" property="id"/>
        <!-- 非主键，用result -->
        <result column="name" property="name"/>
        <result column="email" property="email"/>
        <result column="age" property="age"/>
    </resultMap>
    <select id="selectAllStudents" resultMap="studentMap">
        select id, name, email, age from student
    </select>
~~~

**dao**：

~~~java
List<Student> selectAllStudents();
~~~



## 分页

