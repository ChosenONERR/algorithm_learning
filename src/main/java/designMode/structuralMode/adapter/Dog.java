package designMode.structuralMode.adapter;

/**
 * （3）Adaptee（适配者类）：定义了需要适配的接口。此处是一条狗
 */
public class Dog {
    //狗可以吠叫
    public void shout(){
        System.out.println("狗可以汪汪汪叫！");
    }
    //狗可以跑
    public void run(){
        System.out.println("狗可以跑");
    }
}
