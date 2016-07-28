package info.dabu.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by AlexY on 2016/7/26.
 */
public class JdbcUtil {

    private static String driverClass;
    private static String url;
    private static String user;
    private static String password;


    static {


        try {

            ClassLoader ld = JdbcUtil.class.getClassLoader();
            InputStream in = ld.getResourceAsStream("dbcfg.properties");
            Properties props = new Properties();

            props.load(in);

            driverClass = props.getProperty("driverClass");
            url = props.getProperty("url");
            user = props.getProperty("user");
            password = props.getProperty("password");

            System.out.println("driverClass=" + driverClass);


            JdbcUtil.class.getClass().forName(driverClass);


        } catch (Exception e) {
            throw new RuntimeException("连接数据库出错，请修复！", e);
        }

    }


    public static Connection getConnection() {
        Connection conn = null;

        try {


            conn = DriverManager.getConnection(url, user, password);


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return conn;

    }


    public static void release(ResultSet rs, Statement stmt, Connection conn) {

        if (null != rs) {

            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            rs = null;
        }


        if (null != stmt) {

            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            stmt = null;

        }


        if (null != conn) {

            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            conn = null;

        }


    }


}
