package leetcode.array;

import java.util.Arrays;
import java.util.Stack;

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
 * 2.单调栈 O(n), + 空间复杂度O(n)
 * 栈中保存元素下标，每当数组元素 num[i] > stack[topIndex] , 进行弹栈，并计算栈元素索引的下标差  i-topIndex , 同时把当前元素num[i]压栈
 * 可以看到栈内的元素一定是单调递减的， 因为如果有比栈内元素大的值会进行弹出
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

    /**
     * 单调栈
     * @param T
     * @return
     */
    public int[] dailyTemperatures3(int[] T) {
        int[] result = new int[T.length];
        Arrays.fill(result, 0);
        Stack<Integer> stack = new Stack<>();

        for(int i=0;i<T.length;i++){
            if(stack.isEmpty()){
                stack.push(i);
            } else {
                Integer topIndex = stack.peek();
                if (T[i] <= T[topIndex]) {
                    stack.push(i);
                } else {
                    //不断进行栈顶元素判断是否满足
                    do{
                        topIndex = stack.peek();
                        if(T[i] <= T[topIndex]){
                            stack.push(i);
                            break;
                        }
                        stack.pop();
                        result[topIndex] = i - topIndex;
                    } while(!stack.isEmpty());
                    stack.push(i);
                }
            }
        }

        return result;
    }

    public static void main(String[] args){
        int[] array = {89,62,70,58,47,47,46,76,100,70};
        DailyTemperature dt = new DailyTemperature();
        System.out.println(Arrays.toString(dt.dailyTemperatures3(array)));
    }
}
