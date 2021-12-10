/**
 * @author Bao WJ
 * @date 2021/12/4 17:12
 */
package com.baowj.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static {
        try {
            InputStream in = new FileInputStream(new File("E:\\JAVA\\Java_Learning\\JavaHighLevel\\src\\main\\java\\com\\baowj\\db.properties"));
            Properties properties = new Properties();
            properties.load(in);

            driver = properties.getProperty("jdbc.driver");
            url = properties.getProperty("jdbc.url");
            username = properties.getProperty("jdbc.name");
            password = properties.getProperty("jdbc.pwd");

            // 加载驱动
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // 释放资源
    public static void release() {

    }
}
