package leetcode.todo;

import java.util.Arrays;

/**
 * 581. 最短无序连续子数组
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 你找到的子数组应是最短的，请输出它的长度。
 *
 * 示例 1:
 *
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 说明 :
 *
 * 输入的数组长度范围在 [1, 10,000]。
 * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
 *
 * 思路：1.copy一个数组排序，双指针值比较确定边界 时间复杂度O(nlog+n)  空间复杂度O(n)
 *
 *       2.找出无序数组的左边界L和右边界R, result=R-L+1
 *          2.1  双重循环，for(i)
 *                           for(j=i+1)   当判断到 nums[i] > nums[j] 进行更新左右边界 min(L, i)   max(R, j)
 *
 *          2.2 两次遍历，从左往右遍历，维护一个i左侧的max值和一个右边界right ， 当发生逆序即 max>nums[i]时，更新right；如果升序更新max
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/17 14:54
 */
public class FindUnsortedSubarray {

    public int findUnsortedSubarray(int[] nums) {
        int[] sortNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortNums);
        int left=0;
        int right=nums.length-1;
        while(left<nums.length && nums[left]==sortNums[left]){
            left++;
        }
        while(right>=0 && nums[right]==sortNums[right]){
            right--;
        }
        if (right<left) {
            return 0;
        }
        System.out.println("left:"+left+" right:"+right);
        return right-left+1;
    }

    /**
     * copy from leetcode
     * https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/solution/shi-jian-chao-guo-100de-javajie-fa-by-zackqf/
     * 双指针 O(n)
     *  同时从前往后和从后往前遍历，分别得到要排序数组的右边界和左边界；
     * 寻找右边界：
     * 从前往后遍历的过程中，用max记录遍历过的最大值，如果max大于当前的nums[i]，说明nums[i]的位置不正确，属于需要排序的数组，因此将右边界更新为i，然后更新max；这样最终可以找到需要排序的数组的右边界，右边界之后的元素都大于max；
     * 寻找左边界：
     * 从后往前遍历的过程中，用min记录遍历过的最小值，如果min小于当前的nums[j]，说明nums[j]的位置不正确，应该属于需要排序的数组，因此将左边界更新为j，然后更新min；这样最终可以找到需要排序的数组的左边界，左边界之前的元素都小于min；
     * （从前往后遍历和从后往前遍历两个过程可以分两次循环完成，也可以放一起完成，这样的话就有：j=len-i-1）
     * @param nums
     * @return
     */
    public int findUnsortedSubarray1(int[] nums) {
        int len = nums.length;
        int max = nums[0];
        int min = nums[len-1];
        int l = 0, r = -1;
        for(int i=0;i<len;i++){
            if(max>nums[i]){
                r = i;
            }else{
                max = nums[i];
            }
            if(min<nums[len-i-1]){
                l = len-i-1;
            }else{
                min = nums[len-i-1];
            }
        }
        return r-l+1;
    }

    public int findUnsortedSubarray2(int[] nums) {
        int l=nums.length,r=0;
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[j]<nums[i]){
                    r = Math.max(r, j);
                    l = Math.min(l, i);
                }
            }
        }
        return r>l ? r-l+1 : 0;
    }

    public static void main(String[] args){
        int[] nums = new int[]{2, 6, 4, 8, 10, 9, 15};
        FindUnsortedSubarray fus = new FindUnsortedSubarray();
        System.out.println(fus.findUnsortedSubarray2(nums));
    }
}
