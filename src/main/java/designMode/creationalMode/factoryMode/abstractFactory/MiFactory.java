package designMode.creationalMode.factoryMode.abstractFactory;

public class MiFactory implements AbstractFactory
{
    public TV produceTV(){
        return new MiTV();
    }
    public AirConditioner produceAirConditioner(){
        return new MiAirConditioner();
    }
}