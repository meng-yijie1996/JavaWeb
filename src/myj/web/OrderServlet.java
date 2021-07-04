package myj.web;

import myj.pojo.Cart;
import myj.pojo.User;
import myj.service.OrderService;
import myj.service.impl.OrderServiceImpl;
import myj.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author myj
 * @date 2021/6/29 - 19:48
 */
public class OrderServlet extends BaseServlet{

    private OrderService orderService=new OrderServiceImpl();

    //生成订单
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先获取Cart购物车对象
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        //获取UserId
        User loginUser= (User) req.getSession().getAttribute("user");
        //如果未登录，跳到登录页面
        if (loginUser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            //转发和重定向后，不需要再执行任何代码了，价格return
            return;
        }
        Integer userId = loginUser.getId();

        //调用orderService.createOrder(Cart,UserId),生成订单
        String orderId = orderService.createOrder(cart, userId);
//        String orderId = null;
//        try {
//            orderId = orderService.createOrder(cart, userId);
//            JdbcUtils.commitAndClose();//提交
//        } catch (Exception e) {
//            JdbcUtils.rollbackAndClose();//回滚
//            e.printStackTrace();
//        }

//        //采用重定向后，保存在request域中不在适用，改为保存在session域中
//        req.setAttribute("orderId",orderId);
        req.getSession().setAttribute("orderId",orderId);
//        //请求转发到/pages/cart/checkout.jsp===会导致重复提交，因此采用重定向
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);
        //重定向
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
    //查看所有订单
    protected void showAllOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
    //发货
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
    //查看订单详情
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
    //查看我的订单
    protected void showMyDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
    //签收订单、确认收货
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
