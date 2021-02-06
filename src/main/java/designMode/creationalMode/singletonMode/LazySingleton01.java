package designMode.creationalMode.singletonMode;

/**
 * 单例模式：
 * 1. 单例类自身只能有一个对象实例。
 * 2. 该类必须自己创建这个唯一实例。
 * 3. 该类必须向系统中所有其他对象提供这个实例。
 * 懒汉式
 * 介绍： 3步
 *
 * 说明：  饿汉式的单例模式具有线程不安全的问题，只有添加了 synchronized 和 volatile 关键字之后，才能最大程度保证线程安全
 * 1.为什么要加synchronized？
 *      现在有一种情况：当Singleton类刚被初始化时，有两个线程同时访问getInstance方法，因为instance的值为空，
 *      故这两个线程都满足条件，最终的结果就是new语句被执行了两次。显然这不是我们需要的结果。
 *      所以使用同步synchronized来解决。getInstance方法属于临界区，临界区的内容需要互斥访问，将故获取单例对象的方法
 *      加上synchronized，保证每次操作只有一个线程访问。
 *      P.s：为什么要两次判断声明的对象变量是否为null？
 *           答；假设这一种情况：两个线程同时有两个线程同时访问getInstance方法，因为instance的值为空，故这两个线程都满足
 *               条件，也就是第一层判null两者都通过了；然后又因为synchronized导致互斥访问，线程A先进去，创建了对象，此时
 *               instance已经不为空了，如果此时不再判断一次instance是否为null，那么线程B又会创建一次对象！
 * 2.为什么要加volatile？
 *      其实加了synchronized同步锁还不能保证绝对的安全。为什么呢？这里和JVM编译器的指令重排有关。
 *      在Java中代码 instance = new Singleton( ); 编译器进行编译成3句JVM指令：
 *          memory = allocate( );//1. 给对象分配内存空间
 *          ctorSingleton(memory);//2. 对象初始化
 *          instance = memory;//3. 将singleton对象指向为其分配的内存空间
 *      现在发生这样的一种情况，指令进行了重排序：
 *          memory = allocate( );//1. 给对象分配内存空间
 *          instance = memory;//3. 将singleton对象指向为其分配的内存空间
 *          ctorSingleton(memory);//2. 对象初始化
 *       这时候当线程1执行完1和3（没有执行步骤2的初始化，instance是1个不完整的对象，但此时instance已经是非空了），
 *       线程2又抢占到CPU资源，线程2直接执行第一个检测语句。因为instance是非null，直接返回了一个没有初始化的对象。
 */
public class LazySingleton01 {
    //1.私有化构造方法
    private LazySingleton01(){}
    //2.声明一个对象
    private static volatile LazySingleton01 lazySingleton;
    //3.提供一个静态方法获得单例对象
    public static LazySingleton01 getLazySingleton(){
        if (lazySingleton == null){//第一次检测
            synchronized (LazySingleton01.class){//这里必须使用类锁，因为类锁锁住整个类，对象锁锁住类的实例对象
                if(lazySingleton == null){//第二次检测
                    lazySingleton = new LazySingleton01();
                }
            }
        }
        return lazySingleton;
    }
}
