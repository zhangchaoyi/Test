package leetcode.common.lfu;

import java.util.*;

/**
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。它应该支持以下操作：get 和 put。
 *
 * get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
 * put(key, value) - 如果键已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量时，则应该在插入新项之前，使最不经常使用的项无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除最久未使用的键。
 * 「项的使用次数」就是自插入该项以来对其调用 get 和 put 函数的次数之和。使用次数会在对应项被移除后置为 0 。
 *
 * 进阶：
 * 你是否可以在 O(1) 时间复杂度内执行两项操作？
 *
 * 示例：
 *
 * LFUCache cache = new LFUCache( 2 ); capacity (缓存容量)
 *
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回 1
 * cache.put(3,3);    // 去除 key 2
 * cache.get(2);       // 返回 -1 (未找到key 2)
 * cache.get(3);       // 返回 3
 * cache.put(4,4);    // 去除 key 1
 * cache.get(1);       // 返回 -1 (未找到 key 1)
 * cache.get(3);       // 返回 3
 * cache.get(4);       // 返回 4
 */
public class LFUCache {

    private int capacity;
    /**
     * for data  kv
     */
    private Map<Integer, Integer> data = new HashMap<>();

    /**
     * <key, count>
     */
    private Map<Integer, Integer> dataCountMap = new HashMap<>();
    /**
     * 记录所有的次数，和次数对应的元素， List<Key>中维护最近访问的先后
     * <count, List<Key>>
     */
    private Map<Integer, Set<Integer>> countMap = new TreeMap<>(Integer::compareTo);

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (dataCountMap.containsKey(key)) {
            updateExistKeyCount(key);
        }
        return data.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        if(capacity==0){
            return;
        }
        if (dataCountMap.containsKey(key)) {
            updateExistKeyCount(key);
            data.put(key, value);
        } else if(data.size()>=capacity){
            Integer minCount = getMinCount(countMap);
            Set<Integer> keySet = getMinKeySet(countMap);
            Integer deleteKey = pollSet(keySet);
            data.remove(deleteKey);
            dataCountMap.remove(deleteKey);
            if (keySet==null || keySet.isEmpty()) {
                countMap.remove(minCount);
            } else {
                countMap.put(minCount, keySet);
            }
            addNewKey(key, value);
        } else {
            addNewKey(key, value);
        }
    }

    private void updateExistKeyCount(Integer key){
        //remove
        Integer curCount = dataCountMap.get(key);
        Set<Integer> curSet = countMap.get(curCount);
        curSet.remove(key);
        if (curSet==null||curSet.isEmpty()) {
            countMap.remove(curCount);
        } else {
            countMap.put(curCount, curSet);
        }
        //add
        dataCountMap.put(key, curCount+1);
        Set<Integer> plusOld = countMap.get(curCount+1);
        if(plusOld==null||plusOld.isEmpty()){
            plusOld = new LinkedHashSet<>();
        }
        plusOld.add(key);
        countMap.put(curCount+1, plusOld);
    }

    private Integer pollSet(Set<Integer> set){
        Iterator<Integer> iterator = set.iterator();
        Integer result = iterator.next();
        iterator.remove();

        return result;
    }

    private Integer getMinCount(Map<Integer, Set<Integer>> countMap){
        return countMap.entrySet().iterator().next().getKey();
    }

    private Set<Integer> getMinKeySet(Map<Integer, Set<Integer>> countMap){
        return countMap.entrySet().iterator().next().getValue();
    }

    private void addNewKey(Integer key, Integer value) {
        data.put(key, value);
        dataCountMap.put(key, 1);
        Set<Integer> firstKeyList = countMap.get(1);
        if (firstKeyList==null||firstKeyList.isEmpty()) {
            firstKeyList = new LinkedHashSet<>();
        }
        firstKeyList.add(key);
        countMap.put(1, firstKeyList);
    }

    public static void main(String[] args){
        LFUCache cache = new LFUCache( 3);

        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);
        System.out.println(cache.get(4));       // 返回 1
        System.out.println(cache.get(3));       // 返回 1
        System.out.println(cache.get(2));       // 返回 1
        System.out.println(cache.get(1));       // 返回 1
        cache.put(5,5);    // 去除 key 2
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
        System.out.println(cache.get(5));
//        System.out.println(cache.get(2));       // 返回 -1 (未找到key 2)
//        System.out.println(cache.get(3));       // 返回 3
//        cache.put(4,4);    // 去除 key 1
//        System.out.println(cache.get(1));       // 返回 -1 (未找到 key 1)
//        System.out.println(cache.get(3));       // 返回 3
//        System.out.println(cache.get(4));       // 返回 4
    }
}
