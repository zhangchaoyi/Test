package daily;

import java.util.Arrays;
import java.util.HashMap;

import static com.sun.xml.internal.fastinfoset.util.ValueArray.MAXIMUM_CAPACITY;

/**
 * Created by chris on 3/25/18.
 * 位运算优先级从高到低  & ^ |
 */
public class Test {

    public static void main(String[] args) {
//        System.out.println(8|9&10^11);
//        System.out.println(9&10);
//        System.out.println(8^11);
//        System.out.println(8|3);

        //数组扩容，底层调用本地方法 System.arraycopy
//        int[] a = new int[]{1, 2, 3, 4, 5};
//        a = Arrays.copyOf(a, 10);

//        System.out.println(tableSizeFor(18));
        HashMap<String, String> m = new HashMap<>(5);

    }

    //hashmap初始算法要求 size都是2的n次幂
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
