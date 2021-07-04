package myj.test;

import myj.pojo.Cart;
import myj.pojo.CartItem;
import myj.pojo.Order;
import myj.pojo.OrderItem;
import myj.service.OrderService;
import myj.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author myj
 * @date 2021/6/29 - 19:37
 */
public class OrderServiceTest {
    OrderService orderService=new OrderServiceImpl();

    @Test
    public void createOrder() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(150),new BigDecimal(150)));

        System.out.println(orderService.createOrder(cart, 1));
    }

    @Test
    public void showAllOrders() {
        List<Order> orders = orderService.showAllOrders();
        orders.forEach(System.out::println);
    }

    @Test
    public void sendOrder() {
        orderService.sendOrder("16249667832761");
    }

    @Test
    public void showOrderDetail() {
        List<OrderItem> orderItems = orderService.showOrderDetail("16249667832761");
        orderItems.forEach(System.out::println);
    }

    @Test
    public void showMyOrders() {
        List<Order> orders = orderService.showMyOrders(1);
        orders.forEach(System.out::println);
    }

    @Test
    public void receiveOrder() {
        orderService.receiveOrder("16249667832761");
    }
}