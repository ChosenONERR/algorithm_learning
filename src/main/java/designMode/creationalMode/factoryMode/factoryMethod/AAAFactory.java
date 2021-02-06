package designMode.creationalMode.factoryMode.factoryMethod;

public class AAAFactory implements AbstractFactory {
    @Override
    public AbstractProduct produceFactory() {
        System.out.println("生产 AAA具体工厂");
        return new AAAConcreteProduct();
    }
}
