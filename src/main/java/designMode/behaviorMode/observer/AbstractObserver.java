package designMode.behaviorMode.observer;

/**
 * （3）Observer（观察者类）：对所观察的目标的改变做出反应。
 */
public interface AbstractObserver {
    //观察者对通知action做出反应
    void response();
}
