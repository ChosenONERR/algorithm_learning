package designMode.creationalMode.ObserverMode;

import java.util.Observable;

/**
 * 商品
 */
public class Product  extends Observable{

    // 商品名称
    private String name;
    // 商品价格
    private float price;

    //======================================
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
        //设置变化点
        setChanged();
        //通知观察者
        notifyObservers(new Float(price));
    }
}
