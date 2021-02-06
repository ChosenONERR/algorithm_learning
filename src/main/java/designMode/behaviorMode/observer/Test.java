package designMode.behaviorMode.observer;

public class Test {
    public static void main(String[] args) {
        //1.初始化目标类（猫）
        AbstractSubject cat  = new TomCat();
        //2.初始化观察者
        AbstractObserver mouse, dog;
        mouse = new JerryMouse();
        dog = new SpeikeDog();
        //3.把观察者加入到通知队列中
        cat.register(mouse);
        cat.register(dog);
        //4.执行目标类的通知（action）
        cat.action();
    }
}
