package leetcode.array.binary_search;

import java.util.*;

/**
 * 1300. 转变数组后最接近目标值的数组和
 * 给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，数组的和最接近  target （最接近表示两者之差的绝对值最小）。
 *
 * 如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。
 *
 * 请注意，答案不一定是 arr 中的数字。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [4,9,3], target = 10
 * 输出：3
 * 解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
 * 示例 2：
 *
 * 输入：arr = [2,3,5], target = 10
 * 输出：5
 * 示例 3：
 *
 * 输入：arr = [60864,25176,27249,21296,20204], target = 56803
 * 输出：11361
 *
 * 暴力思路：排序，遍历每个元素，map记录所有接近target的绝对差值
 * O(nlogn + n^n)
 *
 * 二分， 下界arrayMin， 上界arrayMax
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^4
 * 1 <= arr[i], target <= 10^5
 */
public class FindBestValue {

    public int findBestValue(int[] arr, int target) {
        //<absDiff, Res>
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(arr);
        int left = 0;
        int right = arr[arr.length-1];

        while (left <= right){
            int mid = (left+right) >>> 1;
            int sum = calSum(arr, mid);
            putMap(map, Math.abs(target-sum), mid);
            if(sum > target){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        Integer minKey = null;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(minKey == null) {
                minKey = entry.getKey();
            } else {
                if(entry.getKey() < minKey){
                    minKey = entry.getKey();
                }
            }
        }

        return map.get(minKey);
    }

    private void putMap(Map<Integer, Integer> map, Integer key, Integer value){
        Integer v = map.get(key);
        if (Objects.isNull(v)) {
            map.put(key, value);
        } else {
            if(value < v){
                map.put(key, value);
            }
        }
    }

    private int calSum(int[] arr, int mid){
        int sum=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i] < mid){
                sum+=arr[i];
            } else {
                sum+=mid;
            }
        }
        return sum;
    }

    public static void main(String[] args){
        int[] nums = new int[]{60864,25176,27249,21296,20204};
        FindBestValue fbv = new FindBestValue();
        System.out.println(fbv.findBestValue(nums, 56803));

//        System.out.println(Math.abs(Arrays.binarySearch(nums, 0))-1);
//        System.out.println(Math.abs(Arrays.binarySearch(nums, 2))-1);
//        System.out.println(Math.abs(Arrays.binarySearch(nums, 4))-1);
//        System.out.println(Math.abs(Arrays.binarySearch(nums, 6))-1);
//        System.out.println(Arrays.binarySearch(nums, 0));
//        System.out.println(Arrays.binarySearch(nums, 2));
//        System.out.println(Arrays.binarySearch(nums, 4));
//        System.out.println(Arrays.binarySearch(nums, 6));
    }
}
