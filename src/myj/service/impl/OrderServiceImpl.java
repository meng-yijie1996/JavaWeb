package myj.service.impl;

import myj.dao.BookDao;
import myj.dao.OrderDao;
import myj.dao.OrderItemDao;
import myj.dao.impl.BookDaoImpl;
import myj.dao.impl.OrderDaoImpl;
import myj.dao.impl.OrderItemDaoImpl;
import myj.pojo.*;
import myj.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author myj
 * @date 2021/6/29 - 19:32
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();

    //生成订单
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //生成订单号==唯一性:时间戳+用户Id
        String orderId=System.currentTimeMillis()+""+userId;
        //创建一个订单对象
        Order order=new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);

//        int i=12/0;

        //遍历购物车中的每一个商品项，转换成为订单项，并保存到数据库中
        for (Map.Entry<Integer, CartItem> entry:cart.getItems().entrySet()){
            //获取购物车中的每一个商品项
            CartItem cartItem=entry.getValue();
            //每一个商品项都转换为订单项
            OrderItem orderItem=new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            //更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
        }
        //生成订单后，购物车清空
        cart.clear();
        return orderId;
    }

    //查询全部订单
    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    //发货
    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,1);
    }

    //查看订单详情
    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemByOrderId(orderId);
    }

    //查看“我的订单”
    @Override
    public List<Order> showMyOrders(Integer userId) {
        return orderDao.queryOrdersByUserId(userId);
    }

    //签收订单，确认收货
    @Override
    public void receiveOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,2);
    }
}
