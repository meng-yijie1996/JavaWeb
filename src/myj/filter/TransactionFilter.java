package myj.filter;

import myj.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author myj
 * @date 2021/6/30 - 20:51
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JdbcUtils.commitAndClose();//提交事务
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();//回滚事务
            e.printStackTrace();
            throw new RuntimeException(e);//把异常抛给Tomcat统一展示友好的错误提示页面
        }
    }

    @Override
    public void destroy() {

    }
}
