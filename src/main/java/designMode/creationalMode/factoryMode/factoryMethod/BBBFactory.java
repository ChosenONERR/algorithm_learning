package designMode.creationalMode.factoryMode.factoryMethod;

public class BBBFactory implements AbstractFactory {
    @Override
    public AbstractProduct produceFactory() {
        System.out.println("生产 BBB具体工厂");
        return new BBBConcreteProduct();
    }
}
