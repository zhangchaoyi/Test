package leetcode.array;

/**
 * 31. 下一个排列
 *
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 *
 * 3,2,1 → 1,2,3
 *
 * 1,1,5 → 1,5,1
 *
 * 156543 -> 163455
 *
 * 从倒序开始判断，找到 n[i] < num[i+1] , 将(num[i+1], num[end])进行升序排序 从排序后的数组找到 比num[i]大的数交换
 *
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/10/14 18:13
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {

    }
}
