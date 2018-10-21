package lsieun.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
    private static final String url;
    private static final String user;
    private static final String password;
    private static final String driverClass;

    static {
        try{
            InputStream inStream = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");

            Properties props = new Properties();
            props.load(inStream);
            url = props.getProperty("url");
            user = props.getProperty("user");
            password = props.getProperty("password");
            driverClass = props.getProperty("driverClass");

            Class.forName(driverClass);
        }
        catch(IOException ex) {
            System.out.println("读取数据库配置文件出错了。。。");
            throw new RuntimeException(ex);
        }
        catch (ClassNotFoundException ex) {
            System.out.println("数据库驱动程序注册出错了。。。");
            throw new RuntimeException(ex);
        }
    }


    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        }
        catch(SQLException ex) {
            System.out.println("获取数据库连接出错了。。。");
            throw new RuntimeException(ex);
        }
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        closeQuietly(rs);
        closeQuietly(stmt);
        closeQuietly(conn);
    }

    public static void closeQuietly(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
