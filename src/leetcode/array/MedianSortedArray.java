package leetcode.array;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 4. 寻找两个正序数组的中位数
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
        int[] nums1 = new int[]{2};
        int[] nums2 = new int[]{};

        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    /**
     * 二路归并进行插入新数组
     * O(m+n)
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> temp = new ArrayList<>();

        int i=0;//for nums1
        int j=0;//for nums2
        int length = nums1.length + nums2.length;
        boolean even = (length % 2 == 0);
        int mid = (nums1.length + nums2.length)/2;

        while((i + j) <= mid ){
            //分别判断 i j 溢出
            if(i > nums1.length - 1){
                temp.add(nums2[j++]);
                continue;
            }
            if(j > nums2.length - 1){
                temp.add(nums1[i++]);
                continue;
            }

            if(nums1[i] < nums2[j]){
                temp.add(nums1[i++]);
            } else {
                temp.add(nums2[j++]);
            }
        }

        if(even){
            BigDecimal bigDecimal1 = new BigDecimal(temp.get(temp.size() - 1) + temp.get(temp.size() - 2));
            BigDecimal bigDecimal2 = bigDecimal1.divide(new BigDecimal(2));
            return bigDecimal2.doubleValue();
        } else {
            return temp.get(temp.size() - 1);
        }

    }
}
