package myj.web;

import com.google.gson.Gson;
import myj.pojo.Book;
import myj.pojo.Cart;
import myj.pojo.CartItem;
import myj.service.BookService;
import myj.service.impl.BookServiceImpl;
import myj.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author myj
 * @date 2021/6/26 - 10:42
 */
public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    //加入购物车
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("加入购物车！！！");
        System.out.println(req.getParameter("id"));

        //获取请求参数：商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用bookService.queryBookById(id):Book,得到图书的信息
        Book book = bookService.queryBookById(id);
        //把图书信息转化成CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem(CartItem)。添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {//妙
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);

        //保存最后一个添加的商品名称
        req.getSession().setAttribute("lastName",cartItem.getName());

        //重定向回原来商品所在的地址页面
        resp.sendRedirect(req.getHeader("Referer"));

        System.out.println(req.getHeader("Referer"));
    }


    //加入购物车
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("加入购物车！！！");
//        System.out.println(req.getParameter("id"));

        //1.获取请求参数：商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2.调用bookService.queryBookById(id):Book,得到图书的信息
        Book book = bookService.queryBookById(id);
        //3.把图书信息转化成CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //4.调用Cart.addItem(CartItem)。添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {//妙
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);

        //5.保存最后一个添加的商品名称
        req.getSession().setAttribute("lastName",cartItem.getName());

        //6、返回购物车总的商品数量和最后一个添加的商品名称
        Map<String,Object> resultMap=new HashMap<String,Object>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());

        Gson gson=new Gson();
        String resultMapJsonString = gson.toJson(resultMap);
        resp.getWriter().write(resultMapJsonString);

//        //重定向回原来商品所在的地址页面
//        resp.sendRedirect(req.getHeader("Referer"));
//
//        System.out.println(req.getHeader("Referer"));
    }

    //删除商品项
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品编号
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        //获取购物车对象
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        if (cart != null){
            //删除了购物车商品项
            cart.deleteItem(id);
            //重定向回原来购物车展示的页面，即从哪里来，回哪里去。
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    //清空购物车
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取购物车对象
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        if (cart != null){
            //清空购物车
            cart.clear();
            //重定向回原来购物车展示的页面，即从哪里来，回哪里去。
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    //修改商品数量
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数：商品编号、商品数量
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        int count=WebUtils.parseInt(req.getParameter("count"),1);
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null){
            //修改商品数量
            cart.updateCount(id,count);
            //重定向回原来购物车展示的页面，即从哪里来，回哪里去。
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }
}