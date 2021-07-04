package myj.dao.impl;

import myj.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author myj
 * @date 2021/5/28 - 21:28
 */
public abstract class BaseDAO {
    //使用DbUtils操作数据库
    private QueryRunner queryRunner=new QueryRunner();

    /*
    * update()方法，执行insert,delete,update语句
    * 返回值表示影响的行数，-1表示失败。
    * */
    public int update(String sql,Object ...args){
        Connection conn1= JdbcUtils.getConnection();
        try {
            return queryRunner.update(conn1,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /*
    * 查询返回一个javaBean的sql语句
    * type  :  返回的对象类型
    * sql  :  执行的sql语句
    * args  :  sql对应的参数值
    * <T>  :  返回的类型的泛型
    *
    * */
    public <T> T queryForOne(Class<T> type,String sql,Object ...args){
        Connection conn2=JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn2,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /*
     * 查询返回多个javaBean的sql语句
     * type  :  返回的对象类型
     * sql  :  执行的sql语句
     * args  :  sql对应的参数值
     * <T>  :  返回的类型的泛型
     *
     * */
    public <T>List<T> queryForList(Class<T> type,String sql,Object ...args){
        Connection conn3=JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn3,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //执行返回一行一列的sql语句
    public Object queryForSingleValue(String sql,Object ...args){
        Connection conn4=JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn4,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
