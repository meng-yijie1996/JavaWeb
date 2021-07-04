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
 * @date 2021/5/28 - 23:03
 */
public class RegistServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //2.检查：验证码是否正确===>写死，为：abcde
        if ("abcde".equalsIgnoreCase(code)){
            //正确
            //3.检查：用户名是否可用
            if(userService.existsUsername(username)){
                //不可以
                System.out.println("用户名["+username+"]已存在");

                //把回显信息保存到request中
                req.setAttribute("msg","用户名已存在！");
                req.setAttribute("username",username);
                req.setAttribute("email",email);


                //跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                //可用，调用Service保存到数据库
                userService.registUser(new User(0,username,password,email));
                //跳到注册成功页面regist_success.html
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }

        }else{
            //把回显信息保存到request中
            req.setAttribute("msg","验证码错误！");
            req.setAttribute("username",username);
            req.setAttribute("email",email);


            //不正确
            System.out.println("验证码["+code+"]错误");
            //跳回注册页面
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }
}
