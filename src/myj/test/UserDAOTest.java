package myj.test;

import myj.dao.UserDAO;
import myj.dao.impl.UserDAOImpl;
import myj.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author myj
 * @date 2021/5/28 - 22:15
 */
public class UserDAOTest {
    UserDAO userDao=new UserDAOImpl();

    @Test
    public void queryUserByUsername() {
        if(userDao.queryUserByUsername("admin") == null){
            System.out.println("用户名可用");
        }else {
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if(userDao.queryUserByUsernameAndPassword("admin","admin")==null){
            System.out.println("用户名或密码错误，登陆失败");
        }else {
            System.out.println("登陆成功");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(00,"admin11","123456","adinn@qq.com")));
    }
}