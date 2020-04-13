package leetcode.array;

/**
 * https://leetcode-cn.com/problems/trapping-rain-water/
 *  leetcode 42 接雨水
 *  给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *  输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * 思路1：
 * 对于数组中的每个数 arr[i] , lmax 代表 i 左侧最大的数， rmax代表 i 右侧最大的数
 * 则 i 能接的雨水是 min{arr[lmax], arr[rmax]} - arr[i]
 *  此思路下使用暴力破解法 时间复杂度O(n * n)
 *  优化使用备忘录先提前记录一次 i 的 lmax 和 rmax ，时间复杂度 O(n) ， 空间复杂度 O(n)
 *
 * 思路2：
 * 每一次处理找出 0 的数字，以及左 右游标从 i 向两边出发，找出 大于的数，如果有则记录为 1 滴雨水
 * 然后数组整体减 1 ，继续同样的遍历判断
 *
 * 思路3：
 * 双向指针遍历，定义 left 、right 两个游标，定义 lmax 和 rmax 分别代表左边最大的数和右边最大的数
 * 因为当前位置可以接雨水的值由 lmax 和 rmax 中较小的一个数决定， 时间复杂度O(n)，空间复杂度 O(1)
 *
 * 当 lmax < rmax 时，计算左游标left可接雨水的值 = lmax - left；同时left++
 * 当 rmax < lmax 时，计算右游标right可接雨水的值 = rmax - right; 同时right--
 *
 */
public class TrappingRainWater {

    public static void main(String[] args){
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        TrappingRainWater trw = new TrappingRainWater();
        System.out.println(trw.trap3(height));
    }

    /**
     * 思路1
     * @param height
     * @return
     */
    public int trap1(int[] height){
        if (height.length == 0) {
            return 0;
        }
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        leftMax[0] = height[0];
        rightMax[height.length-1] = height[height.length-1];
        //因为从左往右遍历，对于位置 i 来说，最大的数是位于 i-1 ，因此 i 只需要不断与 i-1 的元素比较，放到 i 的位置即可
        //类似于冒泡
        for(int i=1; i<height.length; i++){
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }
        //右侧最大元素同理 应该从右往左遍历
        for(int i=height.length-2; i>=0; i--){
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }

        int sum = 0;
        for(int i=1; i<height.length-1; i++){
            int max = Math.min(leftMax[i], rightMax[i]);
            if(height[i] < max){
                sum += max - height[i];
            }
        }
        return sum;
    }

    /**
     * 思路2 超出时间限制
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int sum = 0;
        boolean needNext = true;
        while(needNext){
            int[] passed = new int[height.length];
            for(int i=1; i<height.length-1; i++){
                if (height[i] == 0 && passed[i] != 1) {
                    int left = i-1;
                    int right = i+1;
                    boolean hasLeftBorder = false;
                    boolean hasRightBorder = false;
                    while(left >= 0){
                        if (height[left] > 0) {
                            hasLeftBorder = true;
                            break;
                        } else {
                            left--;
                        }
                    }
                    while(right < height.length){
                        if (height[right] > 0) {
                            hasRightBorder = true;
                            break;
                        } else {
                            right++;
                        }
                    }
                    if(hasLeftBorder && hasRightBorder){
                        for(int k=left+1; k<right; k++){
                            passed[k] = 1;
                        }
                        sum += (right - left - 1);
                    }
                }
            }
            needNext = false;
            //统一处理减1
            for(int i=0; i<height.length;i++){
                if (height[i] != 0) {
                    height[i] -= 1;
                    if(height[i] > 0){
                        needNext = true;
                    }
                }
            }
        }
        return sum;
    }

    /**
     * 因为 lmax 代表 【0。。。left】中最大的元素
     *      rmax 代表【right...height.length-1】中最大的元素
     *
     * 如果 lmax < rmax , 更新左游标left++； 否则更新右游标 right--;
     * 每次循环都重新更新 lmax 和 rmax
     *
     * @param height
     * @return
     */
    public int trap3(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int sum = 0;
        int lmax = height[0];
        int rmax = height[height.length-1];
        int left = 1;
        int right = height.length - 2;

        while(left < right){
            lmax = Math.max(lmax, height[left]);
            rmax = Math.max(rmax, height[right]);

            if (lmax < rmax) {
                sum += lmax - height[left];
                left++;
            } else {
                sum += rmax - height[right];
                right--;
            }
        }

        return sum;
    }
}
