package myj.service;

import myj.pojo.User;

import javax.jws.soap.SOAPBinding;

/**
 * @author myj
 * @date 2021/5/28 - 22:27
 */
public interface UserService {


    /*
    * 注册用户
    * */
    public void registUser(User user);

    /*
    * 登录
    * */
    public User login(User user);

    /*
    * 检查用户名是否可用
    * */
    public boolean existsUsername(String username);
}
