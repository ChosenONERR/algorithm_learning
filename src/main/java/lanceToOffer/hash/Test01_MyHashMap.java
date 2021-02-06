package lanceToOffer.hash;

/**
 *
 * 题目：自定义hashMap（此处只写了put和get方法）
 *
 * 其余以后补：https://blog.csdn.net/Andychenggang/article/details/88880653
 *
 * 1、先新建 16个 桶即 bucket（16是源码默认的，可以自定义），给桶分别标上 1~16 标号；
 *
 * 2、put 一个键值对之后，用 key 的 hash 值对 16 取余，结果肯定也是一个 1~16 范围内的数值，
 * 把这对键值对放到取余后的值对应的那个的桶里（注意，不可能为每一个 hash 值都创建一个桶，那样的话代价太大，
 * 这里只创建了16个桶，所以很有可能一个桶里放入多个键值对。如果偏巧那个桶里是空的，直接把 key、value 放进去，
 * 如果桶不为空，就要一个一个比对桶里原有的 key 是否和现在要放进去的 key 是同一个（即 hash 值相同且 equals 为 true），
 * 如果是同一个，那么就用新的 value 覆盖替换原来的 value 就行，如果遍历完了，没有一个相同的 key，那么就放到所有 key 的
 * 最后面（jdk1.8之后是放在最前面），这样有多个键值对的桶里就形成了一个链表结构，而多个桶之间是数组元素的关系，
 * 所以说 HashMap 就是数组+链表结构。
 *
 * 3、get 一个 key 的值，也是现根据 key 的 hash 对 16 取余，确定其在数组中的 index，然后判断数组下标为 index 处是否为空，
 * 如果为空，则直接返回空（所以说：HashMap 无法判断 value 为空是 key 为空还是 不存在 key 的节点），
 * 否则遍历 index 处的节点，直到找到 key，返回其对应的 value。
 */
public class Test01_MyHashMap<K, V> implements MyMap<K, V> {

    //默认桶的数量值
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    //默认负载因子值
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;


    //桶的数量
    private float loadFactor = 0;
    //负载因子
    private int initCapacity = 0;
    //hashMap中存储节点信息的数据结构（一个元素类型为Entry的数组）
    private Entry<K, V>[] table = null;

    //hashMap中存储节点信息的数据结构
    class Entry<K, V> implements MyMap.Entry<K, V>{
        K key;
        V value;
        //指针（链表结构）
        Entry<K, V> next;
        // 记录该entry所在数组位置（下标）
        int index;

        Entry(K key, V val, Entry<K, V> next, int index){
            this.key = key;
            this.value = val;
            this.next = next;
            this.index = index;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public Entry<K, V> getNext() {
            return next;
        }
    }

    //默认的构造函数（加载默认数据）
    public Test01_MyHashMap(){
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.initCapacity = DEFAULT_INITIAL_CAPACITY;
        table = new Entry[this.initCapacity];
    }

    //自定义的构造函数（加载自定义数据）
    public Test01_MyHashMap(int initCapacity, float loadFactor){
        this.loadFactor = loadFactor;
        this.initCapacity = initCapacity;
        table = new Entry[this.initCapacity];
    }

    //hash算法
    private int hash(K key){
        int h;
        return (key == null) ? 0 : Math.abs((h = key.hashCode())) ^ (h >>> 16);
    }

    @Override
    public V put(K key, V value) {

        //调用hash算法根据key获得数组下标index
        int index = hash(key) % initCapacity;

        if(table[index] != null){
            //获得该数组在index下的值
            Entry<K, V> entry = table[index];
            //定义一个entry容器
            Entry<K, V> entryTemp = null;

            //判断数组的index位置是否为null
            while(entry != null){

                //不为null，则开始判断
                //判断：比较当前key与数组中的key是否一致（判断分两步：hash和equals）
                if(hash(key) == hash(entry.key) && key.equals(entry.key)){
                    //相同，则
                    // 1.返回原本的value值
                    V oldValue = entry.value;
                    // 2.更新该数组的value值就行了
                    entry.value = value;

                    return oldValue;
                }
                 //不相同，则遍历链表，
                 entry = entry.next;
            }
            // 如果不存在相同的key，则采用【头插法】插入链表中
            table[index].next = new Entry<>(key, value, null, index);
        }else {
            //为null，则直接把Entry插入到数组中

            //错误写法
            //table[index].key = key;
            //table[index].value = value;

            Entry<K, V> newEntry = new Entry<>(key, value, null, index);
            table[index] = newEntry;
        }
        return value;
    }

    @Override
    public V get(K key) {
        //调用hash算法根据key获得数组下标index
        int index = hash(key) % initCapacity;

        Entry<K, V> entry = table[index];

        if(entry == null){
            return null;
        }

        // 遍历index处的链表找到key
        while(entry != null){
            if(entry.key == null && key == null || hash(entry.key) == hash(key) && entry.key.equals(key)){
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }
}
