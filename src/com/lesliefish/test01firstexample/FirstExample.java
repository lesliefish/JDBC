package com.lesliefish.test01firstexample;

import java.sql.*;

public class FirstExample {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/EMP?serverTimezone=GMT%2B8";

    static final String USER = "root";
    static final String PWD = "    ";

    public static void test() {
        Connection connection = null;
        Statement statement = null;

        try {
            // 注册jdbc驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 打开连接
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PWD);

            // 执行查询
            System.out.println("Creating statement...");
            statement = connection.createStatement();
            String sql = "SELECT id, first, last, age FROM Employees";

            ResultSet rs = statement.executeQuery(sql);
            // 从查询结果中提取数据

            while (rs.next()) {
                // 通过列名获取数据
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                // 打印结果
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }

            // 关闭
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/*

Connecting to database...
Creating statement...
ID: 100, Age: 18, First: Zara, Last: Ali
ID: 101, Age: 25, First: Mahnaz, Last: Fatma
ID: 102, Age: 30, First: Zaid, Last: Khan
ID: 103, Age: 28, First: Sumit, Last: Mittal

* */