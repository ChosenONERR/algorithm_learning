package designMode.creationalMode.factoryMode.factoryMethod;

/**
 * 1、定义：工厂父类 用于创建 生产特定产品对象的工厂，实现该接口的特定工厂子类实例化特定产品对象。
 * 2、模式结构：（把工厂角色继续划分）
 * （1）Factory（抽象工厂）：模式的核心类，声明了工厂方法，返回一个产品。
 * （2）ConcreteFactory（具体工厂）：实现抽象工厂中定义的方法，可由客户端调用，返回一个产品实例。
 * （3）Product（抽象产品）：所有具体产品类的父类，提供公共的接口。
 * （4）ConcreteProduct（具体产品）：继承抽象产品角色，实现其抽象方法。
 * 3、评价
 * （1）优点：
 *          1、增加新产品类时无需修改现有系统，符合“开闭原则”；
 * （2）缺点：   增加新产品需要增加新的工厂类，增加了系统的复杂性。
 * 4、模式应用：
 * （1）在Java 集合框架中的 List 和 Set 等集合都实现或继承 java.util.Collection 接口。
 *      该接口中的 iterator ()方法返回一个用于遍历集合的 Iterator 类型的对象，该方法就是工厂方法。
 *      listIterator () 方法用于返回一个具体的 Iterator 迭代器对象，是具体的工厂方法。
 */
public interface AbstractFactory {
    /**
     * 这个就是工厂方法，同于生产 具体工厂
     */
    AbstractProduct produceFactory();
}
