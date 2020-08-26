package leetcode.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶:
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * 示例:
 *
 * LRUCache cache = new LRUCache( 2 ); 缓存容量
 *
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回  1
 * cache.put(3,3);    // 该操作会使得关键字 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4,4);    // 该操作会使得关键字 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/8/26 14:12
 */
public class LRUCache {

    private int capacity;

    private Map<Integer, Integer> map = new HashMap<>();
    private Map<Integer, Integer> latestMap;
    private int priority = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        latestMap = new LinkedHashMap<>(capacity);
    }

    public int get(int key) {
        int v = map.getOrDefault(key, -1);
        if (v != -1) {
            latestMap.put(key, ++priority);
        }
        return v;
    }

    public void put(int key, int value) {
        check(key);
        map.put(key, value);
        latestMap.put(key, ++priority);
    }

    public void check(int key){
        if(map.size() >= capacity && !latestMap.containsKey(key)){
            Integer oldestKey = latestMap.entrySet().stream().sorted(Map.Entry
                            .comparingByValue()).findFirst().get().getKey();
//            System.out.println("oldestKey:"+oldestKey);
            latestMap.remove(oldestKey);
            map.remove(oldestKey);
        }
    }

    public static void main(String[] args){
        LRUCache cache = new LRUCache( 10 );
//        cache.put(1,1);
//        cache.put(2,2);
//        System.out.println(cache.get(1));       // 返回  1
//        cache.put(3,3);    // 该操作会使得关键字 2 作废
//        System.out.println(cache.get(2));      // 返回 -1 (未找到)
//        cache.put(4,4);    // 该操作会使得关键字 1 作废
//        System.out.println(cache.get(1));       // 返回 -1 (未找到)
//        System.out.println(cache.get(3));       // 返回  3
//        System.out.println(cache.get(4));       // 返回  4
//        System.out.println(cache.get(2));
//        cache.put(2,6);
//        System.out.println(cache.get(1));
//        cache.put(1,5);
//        cache.put(1,2);
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(2));
        cache.put(10,13);
        cache.put(3,17);
        cache.put(6,11);
        cache.put(10,5);
        cache.put(9,10);
        System.out.println(cache.get(13));
        cache.put(2,19);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        cache.put(5,25);
        System.out.println(cache.get(8));
        cache.put(9,22);
        cache.put(5,5);
        cache.put(1,30);
        System.out.println(cache.get(11));
        cache.put(9,12);
        System.out.println(cache.get(7));
        System.out.println(cache.get(5));
        System.out.println(cache.get(8));
        System.out.println(cache.get(9));
        cache.put(4,30);
        cache.put(9,3);
        System.out.println(cache.get(9));
        System.out.println(cache.get(10));
        System.out.println(cache.get(10));
        cache.put(6,14);
        cache.put(3,1);
        System.out.println(cache.get(3));
        cache.put(10,11);
        System.out.println(cache.get(8));
        cache.put(2,14);
        System.out.println(cache.get(1));
        System.out.println(cache.get(5));
        System.out.println(cache.get(4));
        cache.put(11,4);
        cache.put(12,24);
        cache.put(5,18);
        System.out.println(cache.get(13));
        cache.put(7,23);
    }
}
