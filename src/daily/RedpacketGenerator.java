package daily;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * n元随机分成m个红包
 * 思路：拆分为 m+1 个数，其中包括n和0，然后随机生成(0,n)之间的m-1个数
 * 然后此n+1个数两两相减，保留差值，恰好m个差值而且和为n
 */
public class RedpacketGenerator {

    public static void main(String[] args) {
        System.out.println(genereateRedpacket(1032, 3));
    }

    public static List<Integer> genereateRedpacket(int n, int m){
        List<Integer> originList = new ArrayList<>(m+1);
        originList.add(n);
        for(int i=0;i<m-1;i++){
            Random r = new Random();
            originList.add(r.nextInt(n));
        }
        originList.add(0);

        Collections.sort(originList);

        List<Integer> result = new ArrayList<>(m);

        for(int i=1;i<originList.size();i++){
            int pre = originList.get(i-1);
            int cur = originList.get(i);
            int diff = cur - pre;
            result.add(diff);
        }
        return result;
    }
}
