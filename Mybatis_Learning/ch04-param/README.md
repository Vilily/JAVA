# MyBatis 传参:package:

### parameterType:pager:

1. **dao接口**中方法参数的数据类型；

2. 值是**java**数据类型**全限定名称**，或者是**mybatis**定义的别名；

3. 常用数据类型对应**别名**如下：

    | 别名       | 映射的类型 |
    | :--------- | :--------- |
    | _byte      | byte       |
    | _long      | long       |
    | _short     | short      |
    | _int       | int        |
    | _integer   | int        |
    | _double    | double     |
    | _float     | float      |
    | _boolean   | boolean    |
    | string     | String     |
    | byte       | Byte       |
    | long       | Long       |
    | short      | Short      |
    | int        | Integer    |
    | integer    | Integer    |
    | double     | Double     |
    | float      | Float      |
    | boolean    | Boolean    |
    | date       | Date       |
    | decimal    | BigDecimal |
    | bigdecimal | BigDecimal |
    | object     | Object     |
    | map        | Map        |
    | hashmap    | HashMap    |
    | list       | List       |
    | arraylist  | ArrayList  |
    | collection | Collection |
    | iterator   | Iterator   |

4. **注意**：**parameterType**可以没有，**mybatis**可以通过**反射**获得，一般不写；

~~~xml
<select id="selectStudentById" parameterType="int" resultType="com.baowj.domain.Student">
    select id, name, email, age from student where id=#{id}
</select>
~~~



### mapper获取"一个简单类型"参数:maple_leaf:

* **java**基本数据类型和**String**类型；

* 使用 **#{任意字符}**获取；

~~~xml
<select id="selectStudentById" parameterType="int" resultType="com.baowj.domain.Student">
    select id, name, email, age from student where id=#{ngsffd}
</select>
~~~



### mapper获取"多个参数":muscle:【@Param】方法

使用**@Param**命名参数

**dao**代码：

~~~java
    /**
     * 在形参定义前面加入 @Param("自定义参数名称")
     */
    List<Student> selectMultiParam(@Param("myName") String name,
                                   @Param("myAge") Integer age);
~~~

**mapper**代码：

~~~xml
<select id="selectMultiParam" resultType="com.baowj.domain.Student">
    select id, name, email, age from student where name=#{myName} or age=#{myAge}
</select>
~~~

 

### mapper获取"多个参数":muscle:【java对象】方法

创建**java**对象保存参数，对象属性数量可以不等于参数数量。

**实体类**代码：

~~~java
package com.baowj.vo;

public class QueryParam {
    private String paramName;
    private Integer paramAge;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Integer getParamAge() {
        return paramAge;
    }

    public void setParamAge(Integer paramAge) {
        this.paramAge = paramAge;
    }
}

~~~

**dao**文件代码：

~~~java
List<Student> selectMultiObject(QueryParam queryParam);
~~~

**xml**代码：

~~~xml
<select id="selectMultiObject" resultType="com.baowj.domain.Student">
    select id, name, email, age from student where
    name=#{paramName, javaType=java.lang.String, jdbcType=VARCHAR}
    or age=#{paramAge, javaType=java.lang.Integer, jdbcType=INTEGER}
</select>

或者

<select id="selectMultiObject" resultType="com.baowj.domain.Student">
    select id, name, email, age from student where
    name=#{paramName}
    or age=#{paramAge}
</select>
~~~



### mapper获取"多个参数":muscle:【按位置】方法

**dao**文件代码：

~~~java
List<Student> selectMultiPosition(String name, Integer age);
~~~

**xml**文件代码：

~~~xml
<select id="selectMultiPosition" resultType="com.baowj.domain.Student">
    select id, name, email, age from student where name=#{arg0} or age=#{arg1}
</select>
~~~



### mapper获取"多个参数":muscle:【map】方法

**dao**代码：

~~~java
List<Student> selectMultiMap(Map<String, Object> map);
~~~

**mapper**代码：

~~~xml
<select id="selectMultiMap" resultType="com.baowj.domain.Student">
    select id, name, email, age from student where name=#{name} or age=#{age}
</select>
~~~

**测试代码**：

~~~java
@Test
public void testSelectMultiPosition(){
    SqlSession sqlSession = MyBatisUtils.getSqlSession();
    StudentDao dao = sqlSession.getMapper(StudentDao.class);
    List<Student> students = dao.selectMultiPosition("李四", 23);
    for(Student s:students)
        System.out.println(s);
}
~~~





