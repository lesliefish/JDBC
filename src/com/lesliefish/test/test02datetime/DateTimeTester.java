package com.lesliefish.test.test02datetime;

public class DateTimeTester {
    public static void test() {
        // util date
        java.util.Date date = new java.util.Date();
        long time = date.getTime();
        System.out.println("the java date is :" + date.toString());

        // sql date xxxx-xx-xx格式
        java.sql.Date sqlDate = new java.sql.Date(time);
        System.out.println("The SQL DATE is: " + sqlDate.toString());

        // 获取打印 SQL TIME
        java.sql.Time sqlTime = new java.sql.Time(time);
        System.out.println("The SQL TIME is: " + sqlTime.toString());

        // 获取打印 SQL TIMESTAMP 时间戳
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(time);
        System.out.println("The SQL TIMESTAMP is: " + sqlTimestamp.toString());
    }
}
