package designMode.creationalMode.factoryMode.abstractFactory;
/**
 * 这个是抽象工厂模式， 抽象工厂模式再也不是特定工厂生产特定产品，而是一个工厂生产一系列产品
 */
public interface AbstractFactory
{
    /**
     * 抽象工厂里面没有“工厂方法”，
     * 抽象工厂里面包含的抽象方法是多个的，但是生产的不是工厂，而是生产一系列抽象产品
     */
    public TV produceTV();
    public AirConditioner produceAirConditioner();
}