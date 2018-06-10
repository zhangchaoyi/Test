package leecode.array;

/**
 * Created by zcy on 18-5-18.
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 输入: [2,2,1]
 * 输出: 1
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * 异或解决 找出唯一数
 */
public class Onlyone {

    public static int singleNumber(int[] nums) {
        int ret = 0;
        for(int i = 0; i<nums.length; i++)
        {
            ret = nums[i]^ret;
        }
        return ret;
    }

    public static void main(String[] args){
        int[] a = new int[]{4,1,2,1,2};

        System.out.println(singleNumber(a));
    }
}
