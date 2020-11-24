package leetcode.array;

import java.util.Arrays;

/**
 * 75. 颜色分类
 *
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 *
 * 示例:
 *
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 *
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/10 14:15
 *
 * 思路，一次遍历，3个下标 left记录0位置， right记录2位置， 从左向右遍历 i
 * 如果i==0,swap(left++, i)  i==1不处理； i==2 swap(right++, i)（如果i的位置仍然是2while swap右指针）
 * 在遍历的过程中，i左边的有 0和1 ，  left左边是0, left当前位即右边非0，   right右边是2，right当前位置和左边非2
 *
 */
public class SortColors {

    public void sortColors(int[] nums) {
        if (nums.length==1) {
            return;
        }
        int left = 0;
        int right = nums.length-1;

        for(int i=0;i<nums.length;i++){
            if(i>right) {
                break;
            }
            //1.可能swap后仍然为2，因此需要while 2.如果swap后是0，则被后续处理
            while (i<=right && nums[i]==2) {
                swap(nums, right--, i);
            }
            if (nums[i]==0) {
                swap(nums, left++, i);
            }
        }
    }

    private void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args){
        SortColors sc = new SortColors();
        int[] nums = new int[]{2,2};
        sc.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
