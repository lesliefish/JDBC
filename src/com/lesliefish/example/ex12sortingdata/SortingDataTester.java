package com.lesliefish.example.ex12sortingdata;

import java.sql.*;

public class SortingDataTester {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/students?serverTimezone=GMT%2B8";

    static final String USER = "root";
    static final String PASS = "    ";

    public static void test() {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            statement = connection.createStatement();

            // 按名称升序
            System.out.println("Fetching records in ascending order...");
            String sql = "SELECT id, first, last, age FROM Registration ORDER BY first ASC";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.print("ID: " + resultSet.getInt("id"));
                System.out.print(", Age: " + resultSet.getInt("age"));
                System.out.print(", First: " + resultSet.getString("first"));
                System.out.print(", Last: " + resultSet.getString("last"));
                System.out.println();
            }

            // 按名称降序.
            System.out.println("Fetching records in descending order...");
            sql = "SELECT id, first, last, age FROM Registration ORDER BY first DESC";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.print("ID: " + resultSet.getInt("id"));
                System.out.print(", Age: " + resultSet.getInt("age"));
                System.out.print(", First: " + resultSet.getString("first"));
                System.out.print(", Last: " + resultSet.getString("last"));
                System.out.println();
            }
            resultSet.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    connection.close();
            } catch (SQLException se) {
            }//
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end test
}//end

/*
Connecting to a selected database...
Connected database successfully...
Creating statement...
Fetching records in ascending order...
ID: 103, Age: 28, First: Sumit, Last: Mittal
ID: 102, Age: 30, First: Zaid, Last: Khan
ID: 100, Age: 30, First: Zara, Last: Ali
Fetching records in descending order...
ID: 100, Age: 30, First: Zara, Last: Ali
ID: 102, Age: 30, First: Zaid, Last: Khan
ID: 103, Age: 28, First: Sumit, Last: Mittal
Goodbye!
*/