package jvm.gc;

/**
 * Created by chris on 3/10/18.
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8  -XX:+PrintGCDetails
 *
 * 9M 新生代可用
 * 当假如allocation4时 进行MinorGC ，而allocation123无法被回收只能被移动到老年代
 * allocation4分配到新生代
 */
public class MinorGc {

    private static final int _1MB = 1024 * 1024;

    public static void testAllocation(){
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];//出现一次Minor GC
    }

    public static void main(String[] args){
        MinorGc minorGc = new MinorGc();
        minorGc.testAllocation();
    }
}
