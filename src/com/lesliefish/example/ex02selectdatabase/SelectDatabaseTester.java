package com.lesliefish.example.ex02selectdatabase;

import java.sql.*;

public class SelectDatabaseTester {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/students?serverTimezone=GMT%2B8";

    static final String USER = "root";
    static final String PASS = "    ";

    public static void test() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end test
}

/*
Connecting to a selected database...
Connected database successfully...
Goodbye!
*/