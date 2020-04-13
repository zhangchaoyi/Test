package leetcode.array.sort;

import java.util.Arrays;

/**
 *  一次快排帮助所选的挡板元素找对位置，之后递归快排 挡板元素 两侧的元素， 一次快排是O(n)
 *
 *
 *  partion 使用头部和尾部的作为挡板区别是
 *
 *  头部：p代表小于挡板的元素    尾部：p代表大于挡板的元素
 *
 *  快排 从小到大 和 从大到小 的区别在于比较时的 小于 还是 大于
 *
 *  平均时间复杂度 O(n * logn)，最优的情况是，每次partion都能返回当前子数组的中间位置
 *  最坏时间复杂度 O(n * n)  数组有序，每次partion的位置都在数组两侧，这样往下递归时少了一个数组
 *
 *  不稳定 体现在 swap上，当unvisited中出现比挡板小的元素时会取 higher区间第一个元素交互
 *  p | lower | higher | unvisited
 *
 *  5 | 4 2 3 | 9 8 7 9 | 1 6.....
 *  快排取出的 1 与 9 互换，这样第一个9与第二个9位置发生了交换
 *  5 | 4 2 3 1 | 8 7 9 9 | 6 .....
 *
 */
public class QuickSort {

    public static void main(String[] args){
//        int[] nums = new int[]{1,2,3,4,5,6,7};
        int[] nums = new int[]{4,6,1,5,5,5};

        System.out.println("input:" + Arrays.toString(nums));

        QuickSort qs = new QuickSort();
        qs.quickSort(nums, 0, nums.length-1);

        System.out.println("final:" + Arrays.toString(nums));
    }

    public void quickSort(int nums[], int start, int end){
        if (start < end) {
            int index = partion(nums, start, end);
            System.out.println(Arrays.toString(nums));
            quickSort(nums, start, index-1);
            quickSort(nums, index+1, end);
        }

    }

    /**
     * 一次快排，找出挡板的index
     * 使用最后一个元素作为挡板
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int partion(int nums[], int start, int end){
        int val = nums[end];
        //p所在位置的元素表示比挡板大
        int p = start;
        for(int i=start;i<end;i++){
            if (nums[i] < val) {
                swap(nums, p++, i);
            }
        }
        //最后需要将最后挡板元素归位，放到 最终的位置 p
        swap(nums, p, end);
        return p;
    }

    /**
     * 使用头部元素作为挡板
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int partion2(int nums[], int start, int end){
        int val = nums[start];
        //位置p代表的元素是 比挡板小
        int p = start;
        for(int i=start+1;i<=end;i++){
            if (nums[i] < val) {
                p++;
                swap(nums, p, i);
            }
        }
        //交换挡板初始位置复位，把挡板放到对应的位置
        swap(nums, start, p);
        return p;
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
