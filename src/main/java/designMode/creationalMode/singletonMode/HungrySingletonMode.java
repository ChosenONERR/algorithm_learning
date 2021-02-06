package designMode.creationalMode.singletonMode;

/**
 * 饿汉式
 */
public class HungrySingletonMode {
    //1.私有化构造方法
    private HungrySingletonMode(){}
    //2.实例化对象，并将其私有化
    private static HungrySingletonMode hungrySingletonMode = new HungrySingletonMode();
    //3.创建一个静态方法返回对象
    public static HungrySingletonMode getHungrySingletonMode(){
        return hungrySingletonMode;
    }
}
