package leetcode.array;

import java.util.Arrays;

/**
 * 739. 每日温度
 *
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 1.暴力解法 O(N^2)
 *
 * 2.单调栈
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/10/10 15:19
 */
public class DailyTemperature {

    //暴力解法
    public int[] dailyTemperatures(int[] T) {
        if (T == null) {
            return null;
        }

        int[] result = new int[T.length];
        Arrays.fill(result, 0);
        for(int i=0;i<T.length;i++){
            for(int j=i+1;j<T.length;j++){
                if (T[j] > T[i]) {
                    result[i] = j-i;
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        int[] array = {73, 74, 75, 71, 69, 72, 76, 73};
        DailyTemperature dt = new DailyTemperature();
        System.out.println(Arrays.toString(dt.dailyTemperatures(array)));
    }
}
