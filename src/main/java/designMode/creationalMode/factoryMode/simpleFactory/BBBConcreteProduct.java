package designMode.creationalMode.factoryMode.simpleFactory;

public class BBBConcreteProduct implements AbstractProduct {
    @Override
    public void produceProduct() {
        System.out.println("具体商品BBB");
    }
}
