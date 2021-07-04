package myj.test;

import myj.pojo.Cart;
import myj.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author myj
 * @date 2021/6/26 - 10:12
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(150),new BigDecimal(150)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(150),new BigDecimal(150)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(150),new BigDecimal(150)));
        cart.deleteItem(1);
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(150),new BigDecimal(150)));
        cart.deleteItem(1);
        cart.clear();
        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(100),new BigDecimal(100)));
        cart.updateCount(1,15);
        System.out.println(cart);
    }
}