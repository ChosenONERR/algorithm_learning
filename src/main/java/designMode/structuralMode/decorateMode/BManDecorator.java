package designMode.structuralMode.decorateMode;

/**
 * 4）ConcreteDecorator（具体装饰者）：具体的装饰对象，给内部持有的具体被装饰对象，增加具体的职责。
 *
 *
 * 具体装饰者B
 */
public class BManDecorator extends Decorator{
    @Override
    public void eat() {
        //调用原本的功能
        super.eat();
        //装饰后增加的功能
        System.out.println("把吃的吐出来");
    }
}
