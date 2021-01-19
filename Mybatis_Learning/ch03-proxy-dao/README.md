# MyBatis 动态代理

~~~java
    @Test
    public void testSelectStudents(){
        /**
         * 使用mybatis动态代理
         */
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> students = dao.selectStudents();
        for(Student student:students)
        {
            System.out.println(student);
        }
    }
~~~

