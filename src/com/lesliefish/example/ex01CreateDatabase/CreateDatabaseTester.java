package com.lesliefish.example.ex01CreateDatabase;

import java.sql.*;

public class CreateDatabaseTester {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/?serverTimezone=GMT%2B8";

    static final String USER = "root";
    static final String PASS = "    ";

    public static void test() {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating database...");
            statement = connection.createStatement();

            String sql = "CREATE DATABASE STUDENTS";
            statement.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
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
}

/*
Connecting to database...
Creating database...
Database created successfully...
Goodbye!
*/