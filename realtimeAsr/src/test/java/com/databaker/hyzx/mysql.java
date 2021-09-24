package com.databaker.hyzx;



import java.sql.*;


public class mysql {

    // 数据库地址

    private static String dbUrl = "jdbc:mysql://8.131.245.106:30000/hyzx?"
            + "useSSL=true&characterEncoding=utf-8&serverTimezone=GMT";

    // 用户名

    private static String dbUserName = "d0112";

    // 密码

    private static String dbPassword = "govmysql01@DataBaker.com";

    // 驱动名称

    private static String jdbcName = "com.mysql.cj.jdbc.Driver";



    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://8.131.245.106:30000/hyzx";
        String USER = "d0112";
        String PWD = "govmysql01@DataBaker.com";

        try {
            //注册加载jdbc驱动
            Class.forName(JDBC_DRIVER);
            //打开连接
            conn = DriverManager.getConnection(DB_URL,USER,PWD);
            //创建执行对象
            stmt = conn.createStatement();
            String testUrl = "select * from nstadmin_users";
            //执行sql语句
            ResultSet rs = stmt.executeQuery(testUrl);
            //ResultSetMetaData rsmd = rs.getMetaData();
            //展开结果集
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}