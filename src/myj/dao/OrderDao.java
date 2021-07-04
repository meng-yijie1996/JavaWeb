package myj.dao;

import myj.pojo.Order;
import myj.pojo.OrderItem;

import java.util.List;

/**
 * @author myj
 * @date 2021/6/29 - 16:52
 */
public interface OrderDao {
    //保存订单
    public int saveOrder(Order order);
    //查询全部订单
    public List<Order> queryOrders();
    //修改订单状态
    public int changeOrderStatus(String orderId,Integer status);
    //根据用户编号，查询订单信息
    public List<Order> queryOrdersByUserId(Integer userId);
}
