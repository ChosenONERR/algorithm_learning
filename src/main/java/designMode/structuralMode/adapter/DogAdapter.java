package designMode.structuralMode.adapter;

/**
 *  结构型模式是将系统中的多个类或对象组合在一起，相互协作来完成更复杂的任务或功能。就好比搭积木，
 *  许多简单积木可以搭建成更复杂、功能更强大的结构。它分为两种形式：类结构型模式和对象结构型模式。
 *  类结构型模式由多个类组合，存在继承和实现关系（这里是类适配器模式）；
 *  对象结构型模式由类和对象组合，存在关联关系（比如车和轮胎的关系）。
 *
 *
 *
 *
 *
 *
 *
 * （2）Adapter（适配器类）：模式的核心类，作为转换器对Target和Adaptee进行适配。此处是作为转换器对Root和dog进行适配
 *适配器模式属于结构型模式的一种，它可以理解为现实生活中的电源适配器、网络适配器等。首先要搞清楚什么是结构型模式。
 *
 *
 * （1）优点：将目标类和适配者类解耦，更换添加适配器方便简单，增加了类的复用性。
 * （2）缺点：类适配器模式一次只能适配一个适配者类，目标抽象类只能为接口，具有一定的局限性；
 *            对象适配器模式很难置换适配者类的方法。
 */
public class DogAdapter extends Dog implements Robot {
    @Override
    public void move() {
        System.out.println("机器人模仿：");
        super.run();
    }

    @Override
    public void speaek() {
        System.out.println("机器人模仿：");
        super.shout();
    }
}
