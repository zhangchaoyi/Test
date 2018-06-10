package leecode.array;

/**
 * Created by chris on 5/13/18.
 * 解法一：遍历k，逐个元素移位
 * 解法二：分别翻转左部分和右部分，再翻转整体
 * 解法三：（当前元素+k）%length 得到新位置，需要构造一个新数组保存
 */
public class SpinArray {

    public void rotate(int[] nums, int k)
    {
        if(nums == null || nums.length == 0 || k % nums.length == 0)
            return;

        int turns = k % nums.length;
        int middle = nums.length - turns;

        reverse(nums, 0, middle-1); // reverse left part
        reverse(nums, middle, nums.length-1); // reverse right part
        reverse(nums, 0, nums.length-1); // reverse whole part
    }

    public void reverse(int[] arr, int s, int e)
    {
        while(s < e)
        {
            int temp = arr[s];
            arr[s] = arr[e];
            arr[e] = temp;

            s++;
            e--;
        }
    }

    public static void main(String[] args){

    }
}
