package leetcode.DynamicPlanning;

/**
 * 55.跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 *
 * 示例 2:
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 *
 * 动态规划
 * boolean[i]表示第i个位置是否可以到达
 * boolean[i] 由 (boolean[j] && (num[j]>(i-j))) ， j<i决定
 * 因为可能会有多个 j 节点可以到达 i ， 因此只要有一个 j 到达 i即满足条件
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/6 11:15
 */
public class CanDump {

    public static void main(String[] args){
        int[] nums = new int[]{3,2,1,0,4};

        CanDump cd = new CanDump();
        System.out.println(cd.canJump(nums));
    }

    public boolean canJump(int[] nums) {
        boolean[] canReach = new boolean[nums.length];
        canReach[0] = true;

        for(int i=1;i<nums.length;i++){
            for(int j=0;j<i;j++){
                if(canReach[i]){
                    continue;
                }
                if (canReach[j] && nums[j] >= (i-j)) {
                    canReach[i] = true;
                }
            }
        }

        return canReach[nums.length-1];
    }
}
