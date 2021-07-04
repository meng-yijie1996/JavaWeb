package myj.web;

import com.google.gson.Gson;
import myj.pojo.User;
import myj.service.UserService;
import myj.service.impl.UserServiceImpl;
import myj.test.UserServletTest;
import myj.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author myj
 * @date 2021/6/7 - 18:29
 */
public class UserServlet extends BaseServlet {

    private UserService userService=new UserServiceImpl();

    //注销
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数username
        String username = req.getParameter("username");
        //2、调用userService.existsUsername()
        boolean existsUsername = userService.existsUsername(username);
        //3.把返回的结果封装成map对象
        Map<String,Object> resultMap=new HashMap<String,Object>();
        resultMap.put("existsUsername",existsUsername);

        Gson gson=new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }

    //注销
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、销毁 Session 中用户登录的信息（或者销毁 Session）
        req.getSession().invalidate();
        //2、重定向到首页（或登录页面）。
        resp.sendRedirect(req.getContextPath());
        System.out.println(req.getContextPath());
    }

        //处理登录的业务
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(3);
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
            //保存用户登录后的信息到Session域中
            req.getSession().setAttribute("user",loginUser);
            //跳到成功页面login_success.html
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    //处理注册的业务
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取 Session 中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session 中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");


        User user=WebUtils.copyParamToBean(req.getParameterMap(),new User());

        //2.检查：验证码是否正确===>写死，为：abcde
        //修改：验证码随机生成
        if (token!=null && token.equalsIgnoreCase(code)){
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
