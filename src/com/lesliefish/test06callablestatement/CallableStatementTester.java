package com.lesliefish.test06callablestatement;

import java.sql.*;

/*
先创建一个存储过程:
DELIMITER $$

DROP PROCEDURE IF EXISTS `emp`.`getEmpName` $$
CREATE PROCEDURE `emp`.`getEmpName`
   (IN EMP_ID INT, OUT EMP_FIRST VARCHAR(255))
BEGIN
   SELECT first INTO EMP_FIRST
   FROM Employees
   WHERE ID = EMP_ID;
END $$

DELIMITER ;

*/

public class CallableStatementTester {
    // JDBC 驱动名称以及数据库URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/EMP?serverTimezone=GMT%2B8";

    //  数据库认证
    static final String USER = "root";
    static final String PASS = "    ";

    public static void test() {
        Connection connection = null;
        CallableStatement statement = null;
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            String sql = "{call getEmpName (?, ?)}";
            statement = connection.prepareCall(sql);

            // 绑定IN参数first, 再绑定OUT参数
            int empID = 103;
            statement.setInt(1, empID);
            // OUT参数需要注册类型registerOutParameter
            statement.registerOutParameter(2, java.sql.Types.VARCHAR);

            // execute方法执行存储过程
            System.out.println("Executing stored procedure...");
            statement.execute();

            // 使用getXXX方法检索 employee name
            String empName = statement.getString(2);
            System.out.println("Emp Name with ID:" + empID + " is " + empName);
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
            }// nothing we can do
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
Executing stored procedure...
Emp Name with ID:103 is Sumit
Goodbye!

*/