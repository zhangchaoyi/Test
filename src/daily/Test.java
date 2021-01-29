package daily;

import java.util.*;

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
//        HashMap<String, String> m = new HashMap<>(5);
//        Date d = new Date();
//        Calendar c = Calendar.getInstance();
//        c.add(Calendar.DAY_OF_YEAR, -91);
//        System.out.println(d);
//        System.out.println(c.getTime());
//        System.out.println(d.compareTo(c.getTime()) < 0);

//        List<String> list = new ArrayList<>();
//        List<String> list2 = new ArrayList<>();
//        System.out.println(list.getClass()==list2.getClass());

        Map<String, Map<String, List<String>>> locationMap = new HashMap<>();

        Map<String, List<String>> gdProvinceMap = new HashMap<>();
        gdProvinceMap.put("佛山", new ArrayList<String>(){{add("高明");add("南海");add("顺德");}});
        gdProvinceMap.put("广州", new ArrayList<String>(){{add("天河");add("海珠");add("白云");}});

        locationMap.put("广东", gdProvinceMap);

        Map<String, List<String>> sdProvinceMap = new HashMap<>();
        sdProvinceMap.put("济南", new ArrayList<String>(){{add("历下");add("历城");add("高新");}});
        sdProvinceMap.put("青岛", new ArrayList<String>(){{add("城阳");add("市南");add("市北");}});

        locationMap.put("广东", gdProvinceMap);
        locationMap.put("山东", sdProvinceMap);

        System.out.println(locationMap);
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
