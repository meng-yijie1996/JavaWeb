package myj.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车对象
 *
 * @author myj
 * @date 2021/6/26 - 9:40
 */
public class Cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    //key是商品编号，value是商品信息
    private Map<Integer,CartItem> items=new LinkedHashMap<Integer,CartItem>();

    //添加商品项
    public void addItem(CartItem cartItem){
        //先查看购物车中是否已经添加过此商品
        //如果已经添加过，则数量累加，总金额更新
        //如果没添加过，直接放在集合中即可
        CartItem item = items.get(cartItem.getId());
        if (item == null){
            //之前没添加过此商品
            items.put(cartItem.getId(),cartItem);
        }else {
            //已经添加过
            item.setCount(item.getCount()+1);//数量累加
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//更新总金额
        }
    }

    //删除商品项
    public void deleteItem(Integer id){
        items.remove(id);
    }

    //清空购物车
    public void clear(){
        items.clear();
    }

    //修改商品数量
    public void updateCount(Integer id,Integer count){
        //先查看购物车中是否有此商品。如果有，修改商品数量，修改总金额。
        CartItem item = items.get(id);
        if (item != null){
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public Integer getTotalCount() {
        Integer totalCount=0;
        for (Map.Entry<Integer,CartItem> entry:items.entrySet()){
            totalCount+=entry.getValue().getCount();
        }
//        for (CartItem value:items.values())
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice=new BigDecimal(0);
        for (CartItem item:items.values()){
            totalPrice=totalPrice.add(item.getTotalPrice());
        }

        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }


}
