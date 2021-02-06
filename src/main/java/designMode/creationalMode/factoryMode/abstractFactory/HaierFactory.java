package designMode.creationalMode.factoryMode.abstractFactory;

public class HaierFactory implements AbstractFactory
{
    public TV produceTV(){
        return new HaierTV();
    }
    public AirConditioner produceAirConditioner(){
        return new HaierAirConditioner();
    }
}