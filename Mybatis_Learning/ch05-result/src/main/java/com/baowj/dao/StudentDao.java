package com.baowj.dao;

import com.baowj.domain.Student;
import com.baowj.vo.QueryParam;
import org.apache.ibatis.annotations.Param;
import org.w3c.dom.ls.LSInput;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    public Student selectStudentById(Integer id);

    /**
     * 在形参定义前面加入 @Param("自定义参数名称")
     */
    List<Student> selectMultiParam(@Param("myName") String name,
                                   @Param("myAge") Integer age);


    int selectCount();

    Map<Object, Object> selectMapById(Integer id);

    List<Student> selectAllStudents();
}
