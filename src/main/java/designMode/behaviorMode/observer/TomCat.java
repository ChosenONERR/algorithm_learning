package designMode.behaviorMode.observer;

/**
 * （2）ConcreteSubject（具体目标）：目标类子类，负责向各个观察者通知。
 */
public class TomCat extends AbstractSubject {
    @Override
    public void action() {
        System.out.println("汤姆猫叫！！！");

        //把事件通知给观察者们
        for(Object obs:observerList){
            ((AbstractObserver) obs).response();
        }
    }
}
