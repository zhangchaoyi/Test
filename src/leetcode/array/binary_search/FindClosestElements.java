package leetcode.array.binary_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 658. 找到 K 个最接近的元素
 *
 * 给定一个排序好的数组，两个整数 k 和 x，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 * 如果有两个数与 x 的差值一样，优先选择数值较小的那个数
 *
 * 输入: [1,2,3,4,5], k=4, x=3
 * 输出: [1,2,3,4]
 *
 * 输入: [1,2,3,4,5], k=4, x=-1
 * 输出: [1,2,3,4]
 *
 * 说明:
 *
 * k 的值为正数，且总是小于给定排序数组的长度。
 * 数组不为空，且长度不超过 104
 * 数组里的每个元素与 x 的绝对值不超过 104
 *
 *
 * 思路：先二分搜索找到 x 的位置index， 在 index-k , index+k 两个位置开始向 x 进行游标遍历(注意边界问题), 直到区间大小为 k
 * 时间复杂度：O(logn) + O(n)
 *
 *
 * 思路2：我们可以将数组中的元素按照与目标 x 的差的绝对值排序，排好序后前 k 个元素就是我们需要的答案
 *
 */
public class FindClosestElements {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if(x < arr[0]){
            return resultArray(arr, 0, k-1);
        }
        if(x > arr[arr.length-1]) {
            return resultArray(arr, arr.length-k, arr.length-1);
        }

        int index = binarySearch(arr, x);
        if(index < 0){
            index = -index-1;
        }

        int l = index-k+1;
        int r = index+k-1;
        if(l<0){
            l=0;
        }
        if(r>arr.length-1){
            r=arr.length-1;
        }
        while(r-l > k-1){
            int diffR = arr[r] - x;
            int diffL = x - arr[l];

            if (diffR>=diffL) {
                r--;
            } else {
                l++;
            }
        }

        return resultArray(arr, l, r);
    }

    /**
     * 二分搜索
     * 边界： 初始 l=0， r=arr.length-1,  //取索引位置
     *        l <= r
     *        int mid = (l+r)/2
     *        l=mid-1        r=mid+1
     * @param arr
     * @param x
     * @return
     */
    public int binarySearch(int[] arr, int x){
        if (arr.length == 1) {
            if(arr[0] == x) {
                return 0;
            } else {
                return -1;
            }
        }
        int l = 0;
        int r = arr.length-1;
        while(l <= r){
            int mid = (l+r)/2;
            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] > x) {
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        return -(l+1);
    }


    public List<Integer> resultArray(int[] arr, int l, int r){
        List<Integer> result = new ArrayList<>();
        for(int i=l;i<=r;i++){
            result.add(arr[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0,0,1,2,3,3,4,7,7,8};
        FindClosestElements fce = new FindClosestElements();
//        System.out.println(fce.findClosestElements2(arr, 3, 5));
        System.out.println(fce.binarySearch(arr, 2));
//        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
//        System.out.println(Collections.binarySearch(list, 2));
    }


    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        List<Integer> ret = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.sort(ret, (a,b) -> a == b ? a - b : Math.abs(a-x) - Math.abs(b-x));
        System.out.println(ret);
        ret = ret.subList(0, k);
        Collections.sort(ret);
        return ret;
    }

}
