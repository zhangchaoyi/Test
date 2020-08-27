package leetcode.common.lru;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 使用map + queue 实现
 * @Author: chaoyi.zhang
 * @Date: 2020/8/27 11:01
 */
public class LRUCacheQueue {

    private int capacity;

    private Map<Integer, Integer> data = new HashMap<>();
    /**
     * 记录所有key的最近访问时间，最新的在最后
     */
    private Queue<Integer> latestQ;

    public LRUCacheQueue(int capacity) {
        this.capacity = capacity;
        latestQ = new ArrayBlockingQueue<>(capacity);
    }

    public int get(int key) {
        if(latestQ.contains(key)){
            latestQ.remove(key);
            latestQ.offer(key);
        }
        return data.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        if(latestQ.contains(key)){
            //旧数据
            latestQ.remove(key);
        } else if(latestQ.size() >= this.capacity){
            //新数据
            Integer oldestKey = latestQ.poll();
            data.remove(oldestKey);
        }

        latestQ.offer(key);
        data.put(key, value);
    }

    public static void main(String[] args){
        LRUCacheQueue cache = new LRUCacheQueue(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3,3);    // 该操作会使得关键字 2 作废
        System.out.println(cache.get(2));      // 返回 -1 (未找到)
        cache.put(4,4);    // 该操作会使得关键字 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4
    }
}
