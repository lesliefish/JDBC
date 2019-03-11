package com.lesliefish.test03transactions;

import java.sql.*;

public class TransactionsTester {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/EMP?serverTimezone=GMT%2B8";

    static final String USER = "root";
    static final String PWD = "    ";

    public static void test() {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PWD);

            connection.setAutoCommit(false);

            System.out.println("Creating statement...");
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            // Employees 表中插入一条数据
            System.out.println("Inserting one row....");
            String SQL = "INSERT INTO Employees VALUES (106, 20, 'Rita', 'Tez')";
            statement.executeUpdate(SQL);

            // 再插入一条
            SQL = "INSERT INTO Employees VALUES (107, 22, 'Sita', 'Singh')";
            statement.executeUpdate(SQL);
            System.out.println("Commiting data here....");
            connection.commit();

            String sql = "SELECT id, first, last, age FROM Employees";
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("List result set for reference....");
            printRs(rs);

            rs.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            // 有错误 回滚操作
            System.out.println("Rolling back data here....");
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            // 释放资源
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
                se2.printStackTrace(); // 再失败也木有法子了
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private static void printRs(ResultSet rs) throws SQLException {
        rs.beforeFirst();
        while (rs.next()) {
            System.out.print("ID: " + rs.getInt("id"));
            System.out.print(", Age: " + rs.getInt("age"));
            System.out.print(", First: " + rs.getString("first"));
            System.out.println(", Last: " + rs.getString("last"));
        }
        System.out.println();
    }
}
