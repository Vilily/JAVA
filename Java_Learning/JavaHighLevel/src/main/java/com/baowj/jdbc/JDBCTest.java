/**
 * @author Bao WJ
 * @date 2021/12/5 10:44
 */
package com.baowj.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        PreparedStatement ppstat = null;
        ResultSet rs = null;

        conn = JDBCUtils.getConnection();
        // 使用问号占位符
        String sql = "select name, region from country where code=?";
        ppstat = conn.prepareStatement(sql);
        // 给参数赋值
        ppstat.setString(1, "ABW");
        rs = ppstat.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getObject("name"));
            System.out.println(rs.getObject("region"));
        }


        rs.close();
        ppstat.close();
        conn.close();

    }
}
