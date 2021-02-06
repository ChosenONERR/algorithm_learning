package designMode.creationalMode.ObserverMode;

import java.util.Observable;
import java.util.Observer;

/**
 * 商品价格观察者
 */
public class ProductPriceObserver implements Observer{

    // 商品价格
    private float price;

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Float){
            price = ((Float) arg).floatValue();

            System.out.println("ProductPriceObserver--->价格发生了变化：" + price);
        }
    }
}
