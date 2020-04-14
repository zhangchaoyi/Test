package leetcode.array.sort;

import java.util.Arrays;

/**
 * 归并排序  先递归再排序
 * 把数组递归拆分为多个部分，直到每个数字
 * 再进行两两合并排序
 *
 *  稳定排序
 * 平均时间复杂度 O(n * logn), 最坏时间复杂度 O(n * logn)
 *  空间复杂度 O(n)
 *
 *  缺点是递归消耗内部空间
 */
public class MergeSort {

    public static void main(String[] args){
        int[] nums = new int[]{4, 2, 3, 1, 6, 5};

        MergeSort ms = new MergeSort();
        ms.mergeSort(nums, 0, nums.length-1);

        System.out.println(Arrays.toString(nums));
    }

    public void mergeSort(int[] nums, int start, int end){
        if(start < end){
            int mid = start + (end-start)/ 2;
            mergeSort(nums, start, mid);
            mergeSort(nums, mid+1, end);
            merge(nums, start, mid, end);
        }
    }

    public void merge(int[] nums, int start, int mid, int end){
        //多申请一位作为哨兵
        int[] leftPart = new int[mid - start + 2];
        leftPart[leftPart.length-1] = Integer.MAX_VALUE;
        int[] rightPart = new int[end - mid + 1];
        rightPart[rightPart.length-1] = Integer.MAX_VALUE;

        int leftCount = 0;
        //先复制出两部分数组
        for(int i=start;i<=mid;i++){
            leftPart[leftCount++] = nums[i];
        }
        int rightCount = 0;
        for(int i=mid+1;i<=end;i++){
            rightPart[rightCount++] = nums[i];
        }

        //两部分数组作插入元数组完成部分排序
        int left = 0;
        int right = 0;
        int index = start;

        while(left < leftPart.length-1 || right  < rightPart.length-1){
            if(leftPart[left] < rightPart[right]){
                nums[index++] = leftPart[left];
                left++;
            } else {
                nums[index++] = rightPart[right];
                right++;
            }
        }

    }
}
