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
 *
 * ======================================================================
 * 升级变种
 * 给定一个非空整数数组，除了两个元素只出现一次以外，其余每个元素均出现两次。找出那两个只出现了一次的元素。
 * 思路：先将所有数进行异或得到 两个数的异或结果 R
 * 将R转换为二进制，取其中一个为1的位，假设为第一位，则将数组中的所有数按第一位为0的分一组，为1的分一组，两组分别进行异或
 * 得到最终的结果 O(n)
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
