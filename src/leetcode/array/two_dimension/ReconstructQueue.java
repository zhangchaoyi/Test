package leetcode.array.two_dimension;

import leetcode.DynamicPlanning.LongestCommonSubstring;

/**
 * 406. 根据身高重建队列
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 *
 * 注意：
 * 总人数少于1100人。
 *
 * 示例
 *
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 * todo
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/10 14:23
 */
public class ReconstructQueue {

    public static void main(String[] args){
        int[][] array = new int[8][5];//{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        array[7][0]=1;
        array[4][4]=1;
        array[7][1]=1;
        array[5][0]=1;
        array[6][1]=1;
        array[5][2]=1;

        LongestCommonSubstring.printArray(array);
    }
}
