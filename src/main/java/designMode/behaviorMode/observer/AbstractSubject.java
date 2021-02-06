package designMode.behaviorMode.observer;

import java.util.ArrayList;

/**
 *
 * 观察者模式属于行为型模式的一种，该模式一般用来描述对象之间的依赖关系。那么首先了解下什么是行为型模式？
 *      创建型模式关注对象的创建过程，结构型模式关注对象与类的组织，而行为型模式则关注对象之间的交互。
 *      通过研究系统中对象之间的相互作用，明确每一个对象的职责。
 * 1、观察者模式定义：定义对象之间的一种一对多的依赖关系，当一个对象状态发生改变时，其相关依赖的对象将被通知并自动更新。
 *                生活中比如股票的涨跌会影响股民的情绪的变化。
 *
 * 2、模式优缺点：
 * （1）优点：将表示层和数据逻辑层分离，在观察目标和观察者之间建立抽象的耦合。
 * （2）缺点：若观察者过多，通知过程耗时。会有导致系统崩溃的可能性出现。
 * 3、模式应用：
 * （1）Java语言提供对观察者模式的支持：在 JDK 的 java.util 包中，提供 Observable 类和 Observer 接口。
 * （2）MVC 架构模式使用了该模式，观察目标就是 Model ，观察者就是 View， Controller 为两者之间的中介。
 *
 * 案例;猫和老鼠（猫、老鼠和狗之间的关系：猫是目标，狗和老鼠是观察者）
 *
 *      （1）Subject（目标类）：定义观察者集合。提供接口增加或删除观察者对象，定义通知方法。
 */
public abstract class AbstractSubject {
    //1.定义观察者集合，用于存放多个观察者
    public ArrayList observerList = new ArrayList();
    //2.往集合中 注册 观察者的方法
    public void register(AbstractObserver observer){
        observerList.add(observer);
    }
    //3.往集合中 移除 观察者的方法
    public void remove(AbstractObserver observer){
        observerList.remove(observer);
    }
    //4。抽象通知方法
    public abstract void action();
}
