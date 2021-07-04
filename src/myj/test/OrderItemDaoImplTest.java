package myj.test;

import myj.dao.OrderItemDao;
import myj.dao.impl.OrderItemDaoImpl;
import myj.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author myj
 * @date 2021/6/29 - 17:53
 */
public class OrderItemDaoImplTest {
    OrderItemDao orderItemDao=new OrderItemDaoImpl();


    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null,"java==AAAAA",15,new BigDecimal(150),new BigDecimal(15*150),"A123456789"));
        orderItemDao.saveOrderItem(new OrderItem(null,"java==BBBBB",15,new BigDecimal(150),new BigDecimal(15*150),"B123456789"));
        orderItemDao.saveOrderItem(new OrderItem(null,"java==CCCCC",15,new BigDecimal(150),new BigDecimal(15*150),"C123456789"));
        orderItemDao.saveOrderItem(new OrderItem(null,"java==DDDDD",15,new BigDecimal(150),new BigDecimal(15*150),"D123456789"));
        orderItemDao.saveOrderItem(new OrderItem(null,"java==EEEEE",15,new BigDecimal(150),new BigDecimal(15*150),"E123456789"));
        orderItemDao.saveOrderItem(new OrderItem(null,"java==FFFFF",15,new BigDecimal(150),new BigDecimal(15*150),"F123456789"));

    }

    @Test
    public void queryOrderItemByOrderId() {
        List<OrderItem> orderItems = orderItemDao.queryOrderItemByOrderId("A123456789");
        orderItems.forEach(System.out::println);
    }
}