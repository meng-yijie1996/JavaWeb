package myj.test;

import myj.dao.OrderDao;
import myj.dao.impl.OrderDaoImpl;
import myj.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author myj
 * @date 2021/6/29 - 17:52
 */
public class OrderDaoImplTest {
    OrderDao orderDao=new OrderDaoImpl();

    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("A123456789",new Date(),new BigDecimal(100),0,1));
        orderDao.saveOrder(new Order("B123456789",new Date(),new BigDecimal(100),0,1));
        orderDao.saveOrder(new Order("C123456789",new Date(),new BigDecimal(50),1,1));
        orderDao.saveOrder(new Order("D123456789",new Date(),new BigDecimal(99),2,1));
        orderDao.saveOrder(new Order("E123456789",new Date(),new BigDecimal(88),1,1));
        orderDao.saveOrder(new Order("F123456789",new Date(),new BigDecimal(11),0,3));
        orderDao.saveOrder(new Order("G123456789",new Date(),new BigDecimal(22),0,4));
        orderDao.saveOrder(new Order("H123456789",new Date(),new BigDecimal(33),1,4));
        orderDao.saveOrder(new Order("I123456789",new Date(),new BigDecimal(44),2,3));
        orderDao.saveOrder(new Order("J123456789",new Date(),new BigDecimal(55),1,3));
    }

    @Test
    public void queryOrders() {
        List<Order> orders = orderDao.queryOrders();
        orders.forEach(System.out::println);
    }

    @Test
    public void changeOrderStatus() {
        orderDao.changeOrderStatus("E123456789",99);
    }

    @Test
    public void queryOrdersByUserId() {
        List<Order> orders = orderDao.queryOrdersByUserId(4);
        orders.forEach(System.out::println);
    }
}