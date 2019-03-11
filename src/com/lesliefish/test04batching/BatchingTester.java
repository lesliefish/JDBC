package com.lesliefish.test04batching;

import java.sql.*;

public class BatchingTester {
    // JDBC 驱动名称以及数据库URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/EMP?serverTimezone=GMT%2B8";

    //  数据库认证
    static final String USER = "root";
    static final String PASS = "    ";

    public static void test() {
        Connection connection = null;
        Statement statement = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开一个连接
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // Create statement
            System.out.println("Creating statement...");
            statement = connection.createStatement();

            // 自动提交置为false
            connection.setAutoCommit(false);

            // 首先 先打印所有数据
            printRows(statement);

            String SQL = "INSERT INTO Employees (id, first, last, age) VALUES(200,'Zia', 'Ali', 30)";
            // 上面的SQL语句加入批处理
            statement.addBatch(SQL);


            SQL = "INSERT INTO Employees (id, first, last, age) VALUES(201,'Raj', 'Kumar', 35)";
            // 上面的SQL语句加入批处理
            statement.addBatch(SQL);

            SQL = "UPDATE Employees SET age = 35 WHERE id = 100";
            // 上面的SQL语句加入批处理
            statement.addBatch(SQL);

            // int[]数组接收返回值
            int[] count = statement.executeBatch();

            // 显式提交
            connection.commit();

            // 打印
            printRows(statement);

            // 清理环境
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
                se2.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

    private static void printRows(Statement stmt) throws SQLException {
        System.out.println("Displaying available rows...");
        String sql = "SELECT id, first, last, age FROM Employees";
        ResultSet resultSet = stmt.executeQuery(sql);

        while (resultSet.next()) {
            System.out.print("ID:" + resultSet.getInt("id"));
            System.out.print(", Age:" + resultSet.getInt("age"));
            System.out.print(", First:" + resultSet.getString("first"));
            System.out.println(", Last:" + resultSet.getString("last"));
        }
        System.out.println();
        resultSet.close();
    }
}

/*
Connecting to database...
Creating statement...
Displaying available rows...
ID:100, Age:18, First:Zara, Last:Ali
ID:101, Age:25, First:Mahnaz, Last:Fatma
ID:102, Age:30, First:Zaid, Last:Khan
ID:103, Age:28, First:Sumit, Last:Mittal
ID:106, Age:20, First:Rita, Last:Tez
ID:107, Age:22, First:Sita, Last:Singh
ID:106, Age:20, First:Rita, Last:Tez
ID:107, Age:22, First:Sita, Last:Singh
ID:106, Age:20, First:Rita, Last:Tez
ID:107, Age:22, First:Sita, Last:Singh

Displaying available rows...
ID:100, Age:35, First:Zara, Last:Ali
ID:101, Age:25, First:Mahnaz, Last:Fatma
ID:102, Age:30, First:Zaid, Last:Khan
ID:103, Age:28, First:Sumit, Last:Mittal
ID:106, Age:20, First:Rita, Last:Tez
ID:107, Age:22, First:Sita, Last:Singh
ID:106, Age:20, First:Rita, Last:Tez
ID:107, Age:22, First:Sita, Last:Singh
ID:106, Age:20, First:Rita, Last:Tez
ID:107, Age:22, First:Sita, Last:Singh
ID:200, Age:30, First:Zia, Last:Ali
ID:201, Age:35, First:Raj, Last:Kumar

Goodbye!
*/