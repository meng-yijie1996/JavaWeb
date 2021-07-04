package myj.service.impl;

import myj.dao.UserDAO;
import myj.dao.impl.UserDAOImpl;
import myj.pojo.User;
import myj.service.UserService;

/**
 * @author myj
 * @date 2021/5/28 - 22:36
 */
public class UserServiceImpl implements UserService {
    private UserDAO userDAO=new UserDAOImpl();

    @Override
    public void registUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDAO.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userDAO.queryUserByUsername(username) == null){
            //null说明没查到，表示可用
            return false;
        }
        return true;
    }
}
