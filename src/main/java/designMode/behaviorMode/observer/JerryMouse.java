package designMode.behaviorMode.observer;

/**
 * （4）ConcreteObserver（具体观察者）：实现更新数据的方法。
 */
public class JerryMouse implements AbstractObserver {
    @Override
    public void response() {
        System.out.println("老鼠Jerry听到猫叫后，赶紧跑！！！");
    }
}
