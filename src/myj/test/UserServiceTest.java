package myj.test;

import myj.pojo.User;
import myj.service.UserService;
import myj.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author myj
 * @date 2021/5/28 - 22:51
 */
public class UserServiceTest {
    UserService userService=new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(0,"bbj168","666666","bbj168@qq.com"));
        userService.registUser(new User(0,"abc168","666666","abc168@qq.com"));


    }

    /*
    * 返回null，说明登录失败
    * 返回有值，登录成功
    * */
    @Test
    public void login() {
        //错误
        System.out.println(userService.login(new User(1, "hhhhh", "iihihi", "hyyyy")));

        //正确
        System.out.println(userService.login(new User(1, "abc168", "666666", "hyyyy")));

    }

    @Test
    public void existsUsername() {
        if(userService.existsUsername("abc1618")){
            System.out.println("用户名存在");
        }else {
            System.out.println("用户名可用");
        }
    }
}