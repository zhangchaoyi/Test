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
 * 思路：O(nlogn + n^3)
 *      不重复，先排序 O(nlogn), 使用一个内部类覆盖equals()和hashcode()方法； 根据hashmap原理hashcode找到桶入口， equals进行逐一的元素比较
 *      嵌套3个for循环 O(n^3)
 * toSubmit
 * @Author: chaoyi.zhang
 * @Date: 2020/12/17 14:24
 */
public class ThreeSum {

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

    private List<Integer> newList(int i, int j, int k){
        List<Integer> l = new ArrayList<>();
        l.add(i);
        l.add(j);
        l.add(k);
        return l;
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

    public static void main(String[] args){
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        ThreeSum ts = new ThreeSum();
        System.out.println(ts.threeSum(nums));
    }
}
