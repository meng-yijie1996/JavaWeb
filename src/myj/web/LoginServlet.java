package myj.web;

import myj.pojo.User;
import myj.service.UserService;
import myj.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 已被UserServlet代替
 *
 * @author myj
 * @date 2021/5/29 - 0:02
 */
public class LoginServlet extends HttpServlet {

    private UserService userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //2.userService.login()登录
        User loginUser = userService.login(new User(0, username, password, null));
        //3.根据login()方法返回结果判断登录是否成功
        if (loginUser==null){   //失败
            //把错误信息和回显的表单项信息，保存到request域中
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);

            //跳回登陆页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {       //成功
            //跳到成功页面login_success.html
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }
}
