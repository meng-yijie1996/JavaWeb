package myj.dao;

import myj.pojo.User;

/**
 * @author myj
 * @date 2021/5/28 - 21:52
 */
public interface UserDAO {




    /*
    * 根据用户名查询用户信息
    * 若返回null,说明无此用户。反之亦然
    * */
    public User queryUserByUsername(String username);

    /*
    * 根据用户名和密码查询用户信息
    * 若返回null,说明用户名或密码错误。反之亦然
    * */
    public User queryUserByUsernameAndPassword(String username,String password);

    /*
    * 保存用户信息
    * */
    public int saveUser(User user);
}
