package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
 * 为什么要取结果中一个为1的位，因为该位为1说明两个唯一数在该位上肯定是存在一个为0一个为1，才会导致异或的结果的该位为1
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
        int[] a = new int[]{4,1,2,1,2,3,3,4,4,5,5,6,6,7,7,8,8,9};

        System.out.println(Arrays.toString(get2SingleNumber(a)));
    }

    /**
     * 找出两个不同的元素
     * @param nums
     * @return
     */
    public static int[] get2SingleNumber(int[] nums){
        //先获取异或结果
        int ret = singleNumber(nums);

        String binary = Integer.toBinaryString(ret);
        StringBuilder sb1 = new StringBuilder();
        //补足8位
        if(binary.length() < 8){
            for(int j=0;j<(8-binary.length());j++){
                sb1.append("0");
            }
        }
        sb1.append(binary);

        //找出第一个为1的位置
        int firstOneIndex = 0;
        for(int i=0; i<sb1.toString().length(); i++){
            if (sb1.toString().charAt(i) == '1') {
                firstOneIndex = i;
                break;
            }
        }
        //将所有数字按firstOneIndex=0/1区分为两组
        List<Integer> zero = new ArrayList<>();
        List<Integer> one = new ArrayList<>();

        for(int i=0; i<nums.length; i++){
            String bin = Integer.toBinaryString(nums[i]);
            StringBuilder sb = new StringBuilder();
            //补足8位
            if(bin.length() < 8){
                for(int j=0;j<(8-bin.length());j++){
                    sb.append("0");
                }
            }
            sb.append(bin);

            if(sb.toString().charAt(firstOneIndex) == '0') {
                zero.add(nums[i]);
            } else {
                one.add(nums[i]);
            }

        }
        //分别获取异或结果
        int[] zeroArray = convertListToArray(zero);
        int[] oneArray = convertListToArray(one);

        int num1 = singleNumber(zeroArray);
        int num2 = singleNumber(oneArray);

        int[] result = new int[2];
        result[0] = num1;
        result[1] = num2;
        return result;
    }

    public static int[] convertListToArray(List<Integer> l){
        int[] array = new int[l.size()];
        for(int i=0;i<l.size();i++){
            array[i] = l.get(i);
        }
        return array;
    }
}
