package leecode.common;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 输入：nums = [21,4,7]
 * 输出：32
 * 解释：
 * 21 有 4 个因数：1, 3, 7, 21
 * 4 有 3 个因数：1, 2, 4
 * 7 有 2 个因数：1, 7
 * 答案仅为 21 的所有因数的和。
 *  
 */
public class FourFactors {

    public static void main(String[] args){
        int[] nums = new int[]{21,4,7};

        FourFactors ff = new FourFactors();
        System.out.println(ff.sumFourDivisors(nums));
    }

    public int sumFourDivisors(int[] nums) {
        int sum = 0;
        for(int num : nums){
            Set<Integer> factors = MathematicalFactor.getAllFactors(num);
            if (factors.size() == 4) {
                sum += factors.stream().collect(Collectors.summingInt(factor -> factor.intValue()));
            }
        }
        return sum;
    }



}
