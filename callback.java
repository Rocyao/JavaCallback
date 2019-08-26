
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author voider
 */
public class callback {

    public static void main(String... args) {
        SyncBuyer buyer = new SyncBuyer(new Store("store"), "buyer", "goods");
        System.out.println(buyer.getOrderResult(buyer.orderGoods()));
        //实体调用SyncBuyer.orderGoods()--A方法
        //Store.returnOrderGoodsInfo()--被A方法调用
        //SyncBuyer.getOrderResult()--A方法回调
    }
}

interface OrderResult {

    public String getOrderResult(String state);
}

class Store {

    private String name;

    Store(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //函数B中方法1,被步骤1方法A调用方法,回调SyncBuyer.getOrderResult()方法 --步骤2
    public String returnOrderGoodsInfo(OrderResult order) {
        System.out.println("Store.returnOrderGoodsInfo()");
        String[] s = {"1...", "2...", "3...", "4...", "5..."};
        int temp = new Random().nextInt(5);        
        return order.getOrderResult(s[temp]);
    }

}

class SyncBuyer implements OrderResult {

    private Store store;
    private String buyer;
    private String goods;

    public SyncBuyer(Store store, String buyer, String goods) {
        this.store = store;
        this.buyer = buyer;
        this.goods = goods;
    }

    //函数A中方法A,实体调用方法--步骤1
    public String orderGoods() {
        System.out.println("SyncBuyer.orderGoods()");
        String goodsState = store.returnOrderGoodsInfo(this);
        return goodsState;
    }

    @Override
    public String getOrderResult(String state) {
        System.out.println("SyncBuyer.getOrderResult()");
        return state;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public Store getStore() {
        return store;
    }

    public String getBuyer() {
        return buyer;
    }

    public String getGoods() {
        return goods;
    }

}
