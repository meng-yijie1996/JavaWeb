package myj.service;

import myj.pojo.Cart;
import myj.pojo.Order;
import myj.pojo.OrderItem;

import java.util.List;

/**
 * @author myj
 * @date 2021/6/29 - 18:46
 */
public interface OrderService {
    //生成订单
    public String createOrder(Cart cart,Integer userId);
    //查询全部订单
    public List<Order> showAllOrders();
    //发货
    public void sendOrder(String orderId);
    //查看订单详情
    public List<OrderItem> showOrderDetail(String orderId);
    //查看“我的订单”
    public List<Order> showMyOrders(Integer userId);
    //签收订单，确认收货
    public void receiveOrder(String orderId);
}
