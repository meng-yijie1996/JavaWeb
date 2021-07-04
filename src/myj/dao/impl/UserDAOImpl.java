package myj.dao.impl;

import myj.dao.UserDAO;
import myj.pojo.User;
import org.hamcrest.Condition;

/**
 * @author myj
 * @date 2021/5/28 - 21:59
 */
public class UserDAOImpl extends BaseDAO implements UserDAO {
    @Override
    public User queryUserByUsername(String username) {
        String sql="select `id`,`username`,`password`,`email` from t_user where username=?";
        return queryForOne(User.class,sql,username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql="select `id`,`username`,`password`,`email` from t_user where username=? and password=?";
        return queryForOne(User.class,sql,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql="insert into t_user(`username`,`password`,`email`) VALUES(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
