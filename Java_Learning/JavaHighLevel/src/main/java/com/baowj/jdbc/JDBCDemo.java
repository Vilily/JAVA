/**
 * @author Bao WJ
 * @date 2021/12/4 11:18
 */
package com.baowj.jdbc;

import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. 加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2. 用户信息和url
        String url = "jdbc:mysql://localhost:3306/";
        String database = "mydata";
        url = url + database + "?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=UTC";
        String username = "root";
        String password = "bwj678";
        // 3.连接
        Connection connection = DriverManager.getConnection(url, username, password);

        // 4.执行SQL statement
        Statement statement = connection.createStatement();

        // 5.执行statement来执行SQL语言
        String sql = "select name, region from country where code='ABW'";

        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.beforeFirst();
        while (resultSet.next()) {
            System.out.println("name=" + resultSet.getObject("name"));
            System.out.println("region=" + resultSet.getObject("region"));
        }

        // 6.关闭连接
        resultSet.close();
        statement.close();
        connection.close();
    }

}
