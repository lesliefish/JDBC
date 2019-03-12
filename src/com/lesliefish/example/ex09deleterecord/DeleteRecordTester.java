package com.lesliefish.example.ex09deleterecord;

import java.sql.*;

public class DeleteRecordTester {
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
            String sql = "DELETE FROM Registration WHERE id = 101";
            statement.executeUpdate(sql);

            // print
            sql = "SELECT id, first, last, age FROM Registration";
            ResultSet resultSet = statement.executeQuery(sql);
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
            }
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
ID: 100, Age: 30, First: Zara, Last: Ali
ID: 102, Age: 30, First: Zaid, Last: Khan
ID: 103, Age: 28, First: Sumit, Last: Mittal
Goodbye!
*/