package designMode.structuralMode.decorateMode;

/**
 * 装饰模式是在不使用继承和不改变原类文件的情况下，动态的扩展一个对象的功能。它是通过创建一个包装对象，
 * 也就是装饰来包裹真实的对象。
 *
 *    这一个解释，引自百度百科，我们注意其中的几点。
 *       1.不改变原类文件。
 *       2。不使用继承。
 *       3，动态扩展（对原有功能进行增强）。
 *
 *   （1） Component（抽象组件又叫被装饰对象的基类）：定义一个对象接口，可以给这些对象动态地添加职责。
 */
public interface Person {
    //人可以吃东西
    void eat();
}
