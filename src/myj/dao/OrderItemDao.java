package myj.dao;

import myj.pojo.OrderItem;

import java.util.List;

/**
 * @author myj
 * @date 2021/6/29 - 16:52
 */
public interface OrderItemDao {
    //保存订单项
    public int saveOrderItem(OrderItem orderItem);
    //根据订单号，查询订单明细
    public List<OrderItem> queryOrderItemByOrderId(String orderId);
}
