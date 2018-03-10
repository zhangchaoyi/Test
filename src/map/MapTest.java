package map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chris on 10/10/17.
 * 内部维护一个hashmap
 */
public class MapTest {

    public static class MapCounter extends ConcurrentHashMap<String, Integer>{
        public void count(String al){
            Integer c = get(al);
            if(c == null) {
                put(al, 1);
            } else {
                put(al, c + 1);
            }
        }
    }

    public static void main(String[] args){
        MapCounter mapCounter = new MapCounter();
        mapCounter.count("A");
        mapCounter.count("B");
        mapCounter.count("A");
        mapCounter.count("C");
        mapCounter.count("B");
        mapCounter.count("D");
        mapCounter.count("E");
        mapCounter.count("A");

        System.out.println(mapCounter);
    }
}
