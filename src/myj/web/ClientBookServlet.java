package myj.web;

import myj.pojo.Book;
import myj.pojo.Page;
import myj.service.BookService;
import myj.service.impl.BookServiceImpl;
import myj.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author myj
 * @date 2021/6/12 - 10:06
 */
public class ClientBookServlet extends BaseServlet{

    private BookService bookService=new BookServiceImpl();

    //处理分页功能
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("经过了前台的ClientBookServlet程序");
        // 1、获取请求的参数pageNo和pageSize
        int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2、调用 bookService.page(pageNo,pageSize),获取Page对象
        Page<Book> page=bookService.page(pageNo,pageSize);
        //不同模块，只要修改url即可获取相应分页条
        page.setUrl("client/bookServlet?action=page");
        // 3、保存Page对象到request域中
        req.setAttribute("page",page);
        // 4、请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    //处理分页功能
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("经过了前台的ClientBookServlet程序");
        // 1、获取请求的参数pageNo、pageSize、min、max
        int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min=WebUtils.parseInt(req.getParameter("min"),0);
        int max=WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);
        // 2、调用 bookService.pageByPrice(pageNo,pageSize,min,max),获取Page对象
        Page<Book> page=bookService.pageByPrice(pageNo,pageSize,min,max);

        StringBuilder sb=new StringBuilder("client/bookServlet?action=pageByPrice");
        //如果有最小价格参数，追加到分页条的地址参数中
        if (req.getParameter("min")!=null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        //如果有最大价格参数，追加到分页条的地址参数中
        if (req.getParameter("max")!=null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        //5.不同模块，只要修改url即可获取相应分页条
        page.setUrl(sb.toString());
        // 3、保存Page对象到request域中
        req.setAttribute("page",page);
        // 4、请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
