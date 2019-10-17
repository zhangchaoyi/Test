package leecode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 *
 *  给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。
 *  请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。
 *  你可以假设 nums1 和 nums2 均不为空。
 *  nums1 = [1, 3]
 *  nums2 = [2]
 *
 * 中位数是 2.0
 *====================================================================================
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 中位数是 (2 + 3)/2 = 2.5
 * @author: zhangchaoyi
 * @date: 2018/8/16
 */
public class MedianSortedArray {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};

        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> temp = new ArrayList<>();

        int i=0;//for nums1
        int j=0;//for nums2
        int m = (nums1.length + nums2.length)/2;

        while((i + j) < m ){
            //分别判断 i j 溢出
            if(i > nums1.length - 1){
                temp.add(nums2[j]);
            }
            if(j > nums2.length - 1){
                temp.add(nums1[i]);
            }

            if(nums1[i] < nums2[j]){
                temp.add(nums1[i]);
            } else {
                temp.add(nums2[j]);
            }
        }

        return temp.get(temp.size() - 1);
    }
}