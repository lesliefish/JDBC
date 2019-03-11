package com.lesliefish.test.test05preparedstatement;

import java.sql.*;

public class PreparedStatementTester {
    // JDBC 驱动名称以及数据库URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/EMP?serverTimezone=GMT%2B8";

    //  数据库认证
    static final String USER = "root";
    static final String PASS = "    ";

    public static void test() {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // 预留语句
            System.out.println("Creating statement...");
            String sql = "UPDATE Employees set age=? WHERE id=?";
            statement = connection.prepareStatement(sql);

            // 给参数绑定值
            statement.setInt(1, 35);  // ge
            statement.setInt(2, 102); // ID

            // 更新ID = 102的age值;
            int rows = statement.executeUpdate();
            System.out.println("Rows impacted : " + rows);

            // 打印
            sql = "SELECT id, first, last, age FROM Employees";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                System.out.print("ID:" + resultSet.getInt("id"));
                System.out.print(",Age:" + resultSet.getInt("age"));
                System.out.print(", First:" + resultSet.getString("first"));
                System.out.println(", Last:" + resultSet.getString("last"));
            }
            resultSet.close();
            statement.close();
            connection.close();
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
    }//end main
}//end JDBCExample


/*
Connecting to database...
Creating statement...
Rows impacted : 1
ID:100,Age:35, First:Zara, Last:Ali
ID:101,Age:25, First:Mahnaz, Last:Fatma
ID:102,Age:35, First:Zaid, Last:Khan
ID:103,Age:28, First:Sumit, Last:Mittal
ID:106,Age:20, First:Rita, Last:Tez
ID:107,Age:22, First:Sita, Last:Singh
ID:106,Age:20, First:Rita, Last:Tez
ID:107,Age:22, First:Sita, Last:Singh
ID:106,Age:20, First:Rita, Last:Tez
ID:107,Age:22, First:Sita, Last:Singh
ID:200,Age:35, First:Zia, Last:Ali
ID:201,Age:35, First:Raj, Last:Kumar
Goodbye!
* */