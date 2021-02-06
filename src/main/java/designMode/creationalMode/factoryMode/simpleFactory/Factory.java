package designMode.creationalMode.factoryMode.simpleFactory;

/**
 *  1、定义：简单工厂模式又叫静态工厂方法模式，它根据传入的参数返回所需要的对象，而不需要知道具体类的名字
 *  2、模式结构：（包含以下角色）
 * （1）Factory（工厂角色）：模式的核心类，提供静态工厂方法，用于创建产品对象。
 * （2）AbstractProduct（抽象产品角色）：所有具体产品类的父类，提供公共的接口。
 * （3）ConcreteProduct（具体产品角色）：继承抽象产品角色，实现其抽象方法。
 *
 * 评价：
 * （1）优点：对象的创建和对象的使用分离，对象的创建由专门的工厂类完成。
 * （2）缺点：
 *      1、工厂类包含了所有产品的创建逻辑，一旦出现问题，整个系统将不能工作；
 *      2、产品数量太多会导致工厂类过于复杂，不利于系统的扩展和维护。
 *
 *   模式应用：
 *   在 JDK 类库中的工具类 java.text.DateFormat 中，DateFormat类提供了一个 getDateInstance( ) 方法（静态工厂方法），
 *   用于格式化本地日期，它有三个重载的方法：
 */
public class Factory {
    //静态工厂方法，用于生产商品
    public static AbstractProduct produce(String name){
        if(name.equalsIgnoreCase("AAA")){
            System.out.println("生产商品AAA");
            return new AAAConcreteProduct();
        }else if(name.equalsIgnoreCase("BBB")){
            System.out.println("生产商品BBB");
            return new BBBConcreteProduct();
        }else {
            return null;
        }
    }
}
