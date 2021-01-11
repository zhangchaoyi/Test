package leetcode.array;

import java.util.*;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * 思路：1. O(nlogn + n^3)
 *      不重复，先排序 O(nlogn), 使用一个内部类覆盖equals()和hashcode()方法； 根据hashmap原理hashcode找到桶入口， equals进行逐一的元素比较
 *      嵌套3个for循环 O(n^3)
 *
 *      2.O(nlogn + n^2)
 *      for第二层循环使用双指针进行查找元素， 假设 a+b+c , for第一层循环固定了a，则相当于两数之和 b+c = sum-a ,
 *      1.如果left+right==sum-a , 左右指针同时移动到下一个元素
 *      2.如果left+right>sum-a , 右指针移动
 *      3.如果left+right<sum-a, 跳出当前循环
 *
 *      因为要求输出不包含重复三元组，因此left和right在移位后要与前一位比较是否相等
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/17 14:24
 */
public class ThreeSum {
//========================超出时间限制=======================================================================================================
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Element> set = new HashSet<>();
        Arrays.sort(nums);

        for(int i=0;i<nums.length-2;i++){
            for(int j=i+1;j<nums.length-1;j++){
                for(int k=j+1;k<nums.length;k++){
                    if (nums[k]+nums[j]+nums[i]==0) {
                        set.add(new Element(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        for(Element e:set){
            res.add(newList(e.i, e.j, e.k));
        }

        return res;
    }

    static class Element{
        private int i;
        private int j;
        private int k;

        Element(int i, int j, int k){
            this.i=i;
            this.j=j;
            this.k=k;
        }

        @Override
        public boolean equals(Object obj) {
            Element e = (Element) obj;
            return e.i == this.i && e.j == this.j && e.k == this.k;
        }

        @Override
        public int hashCode() {
            return 31 * (this.i + this.j + this.k);
        }
    }
//============================================================================================================


    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //去除相同元素
        Arrays.sort(nums);

        for(int i=0;i<nums.length;i++){
            if (i!=0 && nums[i] == nums[i-1]) {
                continue;
            }
            int left=i+1;
            int right=nums.length-1;

            while(left<nums.length && left<right){
                if (left!=i+1 && nums[left]==nums[left-1]) {
                    left++;
                    continue;
                } else if(right!=nums.length-1 && nums[right]==nums[right+1]){
                    right--;
                    continue;
                }

                if(nums[i]+nums[left]+nums[right] == 0) {
                    res.add(newList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                } else if(nums[i]+nums[left]+nums[right] > 0){
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }

    private List<Integer> newList(int i, int j, int k){
        List<Integer> l = new ArrayList<>();
        l.add(i);
        l.add(j);
        l.add(k);
        return l;
    }

    public static void main(String[] args){
        int[] nums = new int[]{-4,-1,-1,0,1,2};
        ThreeSum ts = new ThreeSum();
        System.out.println(ts.threeSum2(nums));
    }
}
