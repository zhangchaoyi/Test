package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * 索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到并返回最大的集合S，S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }且遵守以下的规则。
 *
 * 假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]... 以此类推，不断添加直到S出现重复的元素。
 *
 * 输入: A = [5,4,0,3,1,6,2]
 * 输出: 4
 * 解释:
 * A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
 *
 * 其中一种最长的 S[K]:
 * S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 * @author: zhangchaoyi
 * @date: 2018/8/16
 */
public class ArrayNest {

    public static void main(String[] args) {
        int[] array = new int[]{5,4,0,3,1,6,2};

        System.out.println(arrayNesting2(array));
    }

    public static int arrayNesting(int[] nums){
        int maxCount = 0;
        List<Integer> hasThrough = new ArrayList<>();

        for(int i=0; i<nums.length; i++){
            int curVal = nums[i];
            if(hasThrough.contains(curVal)){
                continue;
            }

            System.out.println(curVal);
            int tempCount = 0;

            while(curVal < nums.length && !hasThrough.contains(curVal)){
                hasThrough.add(curVal);
                curVal = nums[curVal];
                tempCount++;
            }
            if(maxCount < tempCount){
                maxCount = tempCount;
            }

            System.out.println(hasThrough);
        }


        return maxCount;
    }

    /**
     * 优化 不断将元素放到他的值对应的索引的位置上
     * @param nums
     * @return
     */
    public static int arrayNesting2(int[] nums){
        int n = nums.length, res = 0;
        for (int i = 0; i < n; ++i) {
            int cnt = 1;
            while (nums[i] != i && nums[i] != nums[nums[i]]) {
                swap(nums, i, nums[i]);
                printArray(nums);
                ++cnt;
            }
            res = Math.max(res, cnt);
            System.out.println();
        }
        return res;
    }

    private static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void printArray(int nums[]){
        for(int i : nums){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
