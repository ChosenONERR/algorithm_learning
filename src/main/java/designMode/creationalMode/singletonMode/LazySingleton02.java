package designMode.creationalMode.singletonMode;

/**
 * 懒汉式2：静态内部类方式
 */
public class LazySingleton02 {
    private LazySingleton02(){}
    //静态内部类
    private static class Lazy{
        private static final LazySingleton02 INSTANCE = new LazySingleton02();
    }
    public static LazySingleton02 getSingleton(){
        return Lazy.INSTANCE;
    }
}
