package com.lesliefish.test.test07streamingdata;

import java.sql.*;
import java.io.*;

public class StreamingDataTester {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/EMP?serverTimezone=GMT%2B8";

    static final String USER = "root";
    static final String PWD = "    ";

    public static void test() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PWD);

            statement = connection.createStatement();
            createXMLTable(statement);

            // 文件输入流
            File file = new File(StreamingDataTester.class.getResource("/").getPath() +
                    "com/lesliefish/test/test07streamingdata/XML_Data.xml");
            long fileLength = file.length();
            FileInputStream fileInputStream = new FileInputStream(file);

            // 创建预处理语句
            String SQL = "INSERT INTO XML_Data VALUES (?,?)";
            preparedStatement = connection.prepareStatement(SQL);
            // 传值
            preparedStatement.setInt(1, 100);
            preparedStatement.setAsciiStream(2, fileInputStream, (int) fileLength);
            preparedStatement.execute();
            fileInputStream.close();

            // 查询获取行
            SQL = "SELECT Data FROM XML_Data WHERE id=100";
            resultSet = statement.executeQuery(SQL);
            // 获取第一行
            if (resultSet.next()) {
                InputStream xmlInputStream = resultSet.getAsciiStream(1);
                int c;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((c = xmlInputStream.read()) != -1)
                    bos.write(c);
                // 打印结果
                System.out.println(bos.toString());
            }

            resultSet.close();
            statement.close();
            preparedStatement.close();
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
            }// nothing we can do
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end test

    private static void createXMLTable(Statement statement) throws SQLException {
        System.out.println("Creating XML_Data table...");
        String streamingDataSql = "CREATE TABLE XML_Data (id INTEGER, Data LONG)";
        try {
            statement.executeUpdate("DROP TABLE XML_Data");
        } catch (SQLException se) {
        }
        // 创建表
        statement.executeUpdate(streamingDataSql);
    }//end createXMLTable
}//end JDBCExample

/*
Connecting to database...
Creating XML_Data table...
<?xml version="1.0"?>
<Employee>
    <id>100</id>
    <first>Zara</first>
    <last>Ali</last>
    <Salary>10000</Salary>
    <Dob>18-08-1990</Dob>
</ Employee>
Goodbye!
*/