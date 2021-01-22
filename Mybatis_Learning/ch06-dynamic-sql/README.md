# 动态SQL:dancer:



### \<if\>

* **xml**：

    ~~~xml
    <mapper namespace="com.baowj.dao.StudentDao">
    
        <select id="selectStudentIf" resultType="com.baowj.domain.Student">
            select id, name, age, email from student
            where 1 = 1
            <if test="name != null and name != '' ">
                and name = #{name}
            </if>
            <if test="age > 0">
                and age > #{age}
            </if>
        </select>
    </mapper>
    ~~~

* **dao**：

    ~~~java
    List<Student> selectStudentIf(Student student);
    ~~~



### \<where\>

用来包含多个**\<if\>**，当多个**\<if\>**有一个成立时，**\<where\>**会自动增加一个**where**关键字，并去掉**if**中多余的**and**、**or**。

* **xml**：

    ~~~xml
    <select id="selectStudentWhere" resultType="com.baowj.domain.Student">
        select id, name, email, age from student
        <where>
            <if test="name != null and name != '' ">
                name = #{name}
            </if>
            <if test="age > 0">
                and age > #{age}
            </if>
        </where>
    </select>
    ~~~

* **dao**：

    ~~~java
    List<Student> selectStudentWhere(Student student);
    ~~~



### \<foreach\>

循环**java**中的数组，**list**集合。

1. **collection**：接口中方法参数的类型，数组**array**，list集合**list**；
2. **item**：自定义，成员变量名；
3. **open**：循环开始时的字符；
4. **close**：循环结束时的字符；
5. **separator**：集合成员之间的分隔符；

* **xml**：

    ~~~xml
    <select id="selectForeachOne" resultType="com.baowj.domain.Student">
        select * from student where id in
        <foreach collection="list" item="myid" open="(" close=")" separator=",">
            #{myid}
        </foreach>
    </select>
    ~~~

* **dao**：

    ~~~java
    List<Student> selectForeachOne(List<Integer> list);
    ~~~

    

### 代码片段

用来**复用**语句

~~~xml
    <!-- 定义sql片段 -->
    <sql id="studentSql">
        select id, name, age, email from student
    </sql>
    <select id="selectStudentIf" resultType="com.baowj.domain.Student">
        <!-- 使用代码片段 -->
        <include refid="studentSql"/>
        where 1 = 1
        <if test="name != null and name != '' ">
            and name = #{name}
        </if>
        <if test="age > 0">
            and age > #{age}
        </if>
    </select>
~~~



