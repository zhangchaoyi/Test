package leetcode.array;

/**
 * 11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 示例：
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 *
 * 思路，for两层遍历   O(n^2)
 *          for(i -> {0, n})
 *             for(j -> {i+1, n})
 *              找到最大值
 *
 *      左右双指针   O(n)
 *      左右双指针往中间，每次移动两个指针中较小的那一个值
 *
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/10/23 15:53
 */
public class MaxArea {

    public int maxArea(int[] height) {
        int tempMax = 0;
        for(int i=0;i<height.length;i++){
            for(int j=i+1;j<height.length;j++){
                int temp = Math.min(height[i], height[j]) * (j-i);
                tempMax = Math.max(tempMax, temp);
            }
        }
        return tempMax;
    }

    public int maxArea2(int[] height) {
        int left = 0;
        int right = height.length-1;
        int max = 0;

        while(left < right){
            max = Math.max(max, getArea(left, right, height));
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return max;
    }

    private int getArea(int left, int right, int[] height){
        return Math.min(height[left], height[right]) * (right-left);
    }

    public static void main(String[] args){
        MaxArea ma = new MaxArea();
        int[] height = new int[]{2,3,10,5,7,8,9};
        System.out.println(ma.maxArea2(height));
    }
}
