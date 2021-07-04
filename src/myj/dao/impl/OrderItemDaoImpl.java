package myj.dao.impl;

import myj.dao.OrderItemDao;
import myj.pojo.OrderItem;

import java.util.List;

/**
 * @author myj
 * @date 2021/6/29 - 17:04
 */
public class OrderItemDaoImpl extends BaseDAO implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql="insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql="select `id`,`name`,`count`,`price`,`total_price` totalPrice,`order_id` orderId from t_order_item where order_id=?";
        return queryForList(OrderItem.class,sql,orderId);
    }
}
