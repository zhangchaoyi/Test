package leetcode.array;

/**
 * 给出一个数组A，找到最大的 A[i] - A[j] , i>j
 *
 * 思路：遍历数组A[i] , 维护A[i]之前的最小的数， 同时计算最大的差值  O(n)
 */
public class FIndMaxDiff {

    public static void main(String[] args) {
        int[] nums = new int[]{3,4,6,9,1,0,15,5};
        FIndMaxDiff diff = new FIndMaxDiff();
        System.out.println(diff.getMin(nums));
    }

    public int getMin(int[] nums){
        if(nums==null||nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        int left=0;//用于记录最终结果的j
        int right=0;//用于记录最终结果的i
        int res = Integer.MIN_VALUE;
        int curMinIndex = 0;

        for(int i=1;i<nums.length;i++){
            //更新res max
            if(nums[i]-nums[curMinIndex] > res){
                res = nums[i]-nums[curMinIndex];
                left = curMinIndex;
                right = i;
            }

            if(nums[i]<nums[curMinIndex]){
                curMinIndex = i;
            }
        }
        System.out.println("left:"+left+" right:"+right);
        return res;
    }
}
