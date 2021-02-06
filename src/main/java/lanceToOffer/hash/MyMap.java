package lanceToOffer.hash;

/**
 * 自定义hashMap接口
 * @param <K> key的数据类型
 * @param <V> value的数据类型
 */
public interface MyMap<K, V> {
    //put方法：添加一个节点元素
    V put(K key, V value);

    //get方法：获得一个节点元素的值
    V get(K key);

    //hashMap的节点类型
    interface Entry<K ,V>{
        //获取节点中的key
        K getKey();

        //获取节点中的value
        V getValue();
    }
}
