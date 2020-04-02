package leetcode.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
    给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。

 * @author: zhangchaoyi
 * @date: 2018/8/15
 */
public class ThirdMaxNumber {

    public static void main(String args[]){
//        int[] array = new int[]{2, 3, 4, 2, 10, 5, 6};
        int[] array = new int[]{1,1,2};
        ThirdMaxNumber tmn = new ThirdMaxNumber();
        System.out.println(tmn.thirdMax(array));

    }

    public int thirdMax(int[] nums){
        if(nums.length < 3){
            int max = nums[0];
            for(int i=1; i<nums.length; i++){
                if(max < nums[i]){
                    max = nums[i];
                }
            }
            return max;
        }

        //保存前3大的数字
        List<Integer> sortedList = new ArrayList<>(3);

        for(int num : nums) {
            if(sortedList.contains(num)){
                continue;
            }
            if(sortedList.size() < 3){
                sortedList.add(num);
                if(sortedList.size() == 3){
                    sortedList = sortedList.stream().sorted(Comparator.comparingInt(Integer::intValue).reversed()).collect(Collectors.toList());
                }
                continue;
            }

            if(num > sortedList.get(0)){
                 //重新调整
                sortedList.set(2, sortedList.get(1));
                sortedList.set(1, sortedList.get(0));
                sortedList.set(0, num);

            } else if(num > sortedList.get(1)){
                sortedList.set(2, sortedList.get(1));
                sortedList.set(1, num);

            } else if(num > sortedList.get(2)){
                sortedList.set(2, num);
            }
        }

        if(sortedList.size() < 3){
            Collections.sort(sortedList);
            return sortedList.get(sortedList.size() - 1);
        }

        return sortedList.get(2);
    }
}
