package myj.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * @author myj
 * @date 2021/5/27 - 20:19
 */
public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns=new ThreadLocal<Connection>();

    static{
        try {
            Properties properties = new Properties();
            //读取jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            //创建了数据库连接池
            dataSource=(DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

//            System.out.println(dataSource.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//
//    }

    //获取数据库连接池中的连接
    public static Connection getConnection(){
        Connection conn= conns.get();//再次取连接，conns中已经有了，确保是同一个连接
        if (conn == null){//未保存以前，为空。
            try {
                conn= dataSource.getConnection();//为空，就从数据库连接池里取
                conns.set(conn);//保存到ThreadLocal中，供后面的JDBC操作使用
                conn.setAutoCommit(false);//设置为手动管理事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    //提交事务，并关闭、释放连接
    public static void commitAndClose(){
        Connection connection=conns.get();
        if (connection != null){//不为null，说明之前使用过连接，操作过数据库。
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove()操作，否则就会出错。因为：Tomcat底层使用了线程池技术
        conns.remove();
    }

    //回滚事务，并关闭、释放连接
    public static void rollbackAndClose(){
        Connection connection=conns.get();
        if (connection != null){//不为null，说明之前使用过连接，操作过数据库。
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove()操作，否则就会出错。因为：Tomcat底层使用了线程池技术
        conns.remove();
    }

//    //关闭连接，放回数据库连接池
//    public static void close(Connection conn){
//        if (conn != null){
//            try {
//                conn.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
