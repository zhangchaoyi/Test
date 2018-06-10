package leecode.array;

import java.util.*;

/**
 * Created by zcy on 18-5-18.
 * 给定两个数组，写一个方法来计算它们的交集。

 例如:
 给定 nums1 = [1, 2, 2, 1], nums2 = [2, 2], 返回 [2, 2].
 */
public class Intersect {

    public static int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        Map<Integer, Integer> results = new HashMap<>();

        for(int i=0;i<nums1.length;i++){
            Integer t = results.get(nums1[i]);
            t = t==null?0:t;
            results.put(nums1[i], t+1);
        }
        Map<Integer, Integer> checkMap = new HashMap<>();

        for(int i=0;i<nums2.length;i++){
            Integer t = checkMap.get(nums2[i]);
            t = t==null?0:t;
            checkMap.put(nums2[i], t+1);
        }

        List<Integer> l = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : results.entrySet()){
            Integer valueNum = entry.getKey();
            if(checkMap.containsKey(valueNum)) {
                int t1 = entry.getValue().intValue();
                int t2 = checkMap.get(valueNum).intValue();
                int len = t1 <= t2 ? t1 : t2;
                for(int i=0; i<len; i++){
                    l.add(valueNum.intValue());
                }
            }
        }

        int[] result = new int[l.size()];
        for(int i=0 ;i<l.size(); i++){
            result[i] = l.get(i);
        }
        return result;
    }

    //先进行排序，再两个数组逐项进行比较
    public static int[] intersectSort(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] save = new int[len1 > len2 ? len1:len2];
        int i=0,j=0,k=0;

        while(i<len1&&j<len2) {
            if(nums1[i]==nums2[j]) {
                save[k]=nums1[i];
                i++;j++;k++;
            }else if(nums1[i]<nums2[j]) {
                i++;
            }else {
                j++;
            }
        }
        return Arrays.copyOf(save,k);
    }

    public static void main(String[] args){
        int[] num1 = new int[]{1, 2, 2, 1};
        int[] num2 = new int[]{2};

        System.out.println(Arrays.toString(intersect(num1, num2)));
    }
}
