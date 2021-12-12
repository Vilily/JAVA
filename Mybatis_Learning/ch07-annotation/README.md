# 使用注解写SQL

## 步骤

#### 1.绑定Mapper

`mybatis.xml`文件添加**类**mapper

~~~xml
    <mappers>
        <mapper class="com.baowj.dao.StudentMapper"/>
    </mappers>
~~~

#### 2.编写DAO

使用**注解**写**SQL**

~~~java
public interface StudentMapper {

    @Select("select * from student")
    List<Student> getStudents();
}
~~~

#### 3.测试

~~~java
public class StudentMapperTest {
    @Test
    public void testGetStudents() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.getStudents();
        for (Student student : students) {
            System.out.println(student);
        }
        sqlSession.close();
    }
}
~~~



## 增删改查

#### 增

~~~java
@Insert("insert into student(id, name, email, age) values (#{id}, #{name}, #{email}, #{age})")
int addStudent(Student student);
~~~

#### 删

~~~java
@Delete("delete from student where id=#{id}")
int deleteStudent(@Param("id") int id);
~~~

#### 改

~~~java
@Update("update student set name=#{name} where id=#{id}")
int updateStudentName(@Param("id") Integer id, @Param("name") String name);
~~~

#### 查

~~~Java
@Select("select * from student where id = #{id}")
Student getUserByID(@Param("id") int id);
~~~

