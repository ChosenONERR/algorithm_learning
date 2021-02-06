package designMode.behaviorMode.observer;

/**
 * （4）ConcreteObserver（具体观察者）：实现更新数据的方法。
 */
public class SpeikeDog implements AbstractObserver {
    @Override
    public void response() {
        System.out.println("斗牛犬Speike听到猫叫后，要去欺负猫！！！");
    }
}
