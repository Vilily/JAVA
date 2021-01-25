package com.baowj.controller;

import com.baowj.entity.Student;
import com.baowj.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        String strName = request.getParameter("name");
        String strEmail = request.getParameter("email");
        String strAge = request.getParameter("age");

        // 原始方法实现
//        String config = "applicationContext.xml";
//        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);

        // 监控器实现
        WebApplicationContext ctx = null;
//        String key = WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE;
//        Object attr = getServletContext().getAttribute(key);
//        if(attr != null){
//            ctx = (WebApplicationContext) attr;
//        }

        // 框架方法获取容器对象
        ServletContext sc = getServletContext();
        ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
        System.out.println("容器:" + ctx);
        // 获取service
        StudentService studentService = (StudentService)ctx.getBean("studentService");
        Student student = new Student();
        student.setId(Integer.parseInt(strId));
        student.setAge(Integer.parseInt(strAge));
        student.setEmail(strEmail);
        student.setName(strName);
        studentService.addStudent(student);

        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}
