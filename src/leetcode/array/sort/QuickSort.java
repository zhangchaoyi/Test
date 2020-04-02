package leetcode.array.sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args){
        int[] nums = new int[]{3,2,5,6,1};

        QuickSort qs = new QuickSort();
        qs.quickSort(nums, 0, nums.length-1);

        System.out.println(Arrays.toString(nums));
    }

    public void quickSort(int nums[], int start, int end){
        if (start < end) {
            int index = partion(nums, start, end);
            quickSort(nums, start, index-1);
            quickSort(nums, index+1, end);
        }

    }

    /**
     * 一次快排，找出挡板的index
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int partion(int nums[], int start, int end){
        int val = nums[start];
        int p = start;
        for(int i=start+1;i<=end;i++){
            if (nums[i] < val) {
                swap(nums, p++, i);
            }
        }
        swap(nums, p, end);
        return p;
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
