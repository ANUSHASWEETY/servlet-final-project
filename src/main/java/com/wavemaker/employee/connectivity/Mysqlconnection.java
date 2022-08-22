package com.wavemaker.employee.connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysqlconnection {

    private static String MYSQL_HOST = "jdbc:mysql://localhost:3306/test_jdbc";
    private static String MYSQL_USER_NAME = "root";
    private static String MYSQL_PASSWORD = "mygoal@1bank";
    private static volatile Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("failed to connect with driver",e);
        }
    }
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(MYSQL_HOST, MYSQL_USER_NAME, MYSQL_PASSWORD);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                System.out.println("SQL Exception while Creating Mysql Connection " + MYSQL_HOST + " With User " + MYSQL_USER_NAME);
            }
        }
        return connection;
    }
    public void closeConnection(Connection connection)  {
        try{
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

