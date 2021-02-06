package designMode.structuralMode.decorateMode;

/**
 * 3） Decorator（装饰者抽象类）：装饰器，是一个抽象类，实现component接口，并定义该接口作为成员变量接收被装饰者实例
 *     是抽象构件类的子类，用来给具体构件增加职责，具体职责在子类中实现。
 */
public abstract class Decorator implements Person{
    //并定义一个与Component接口一致的接口
    private Person person;
    //setter方法，装饰者抽象类中需要传入具体被装饰对象
    public void setPerson(Person person) {
        this.person = person;
    }
    //对具体装饰则的eat方法进行加强，但是此处不具体实现，留在该类的子类中实现
    @Override
    public void eat() {
        person.eat();
    }
}
