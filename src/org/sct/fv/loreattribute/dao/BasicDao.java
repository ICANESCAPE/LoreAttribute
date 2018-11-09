package org.sct.fv.loreattribute.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class BasicDao {
    private static Connection connection;

    public static void setConnection(Connection connection) {
        BasicDao.connection = connection;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("错误：" + e.getMessage());
        }
    }

    public static boolean isClose() {
        try {
            if (connection != null &&
                    connection.isClosed()) {
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("错误: " + e.getMessage());
        }
        return false;
    }

}
