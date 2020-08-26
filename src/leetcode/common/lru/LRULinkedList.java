package leetcode.common.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 基于linkedHashMap的实现，十分简单，需要构造方法 accessOrder=true，覆盖removeEldestEntry方法
 *
 * 当accessOrder设置为false时，会按照插入顺序进行排序，当accessOrder为true是，会按照访问顺序
 * （也就是插入和访问都会将当前节点放置到尾部，尾部代表的是最近访问的数据
 */
class LRULinkedList {
    int capacity;
    LinkedHashMap<Integer, Integer> cache;

    public LRULinkedList(int capacity) {
        this.capacity = capacity;
        cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return cache.size() > capacity;
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
}