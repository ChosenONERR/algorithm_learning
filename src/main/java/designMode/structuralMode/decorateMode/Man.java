package designMode.structuralMode.decorateMode;

/**
 *  2） ConcreteComponent（具体组件又叫具体被装饰对象）：定义一个对象，可以给这个对象添加一些职责。
 */
public class Man implements Person {
    //原本的功能
    @Override
    public void eat() {
        System.out.println("吃一顿饭");
    }
}
