package leetcode.array.two_dimension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 * 示例 1:
 *
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2:
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *  
 *
 * 提示：
 *
 * intervals[i][0] <= intervals[i][1]  即同一行的列数递增
 *
 *  思路: 先按二维数组第一列进行排序， Arrays.sort(intervals, Comparator.comparingInt(eachArray -> eachArray[0]));
 *       在逐行进行边界判断区间合并 保存区间
 *       lastHead & lastTail 进行上一次的保存, 与当次循环 curHead & curTail 进行边界判断更新 lastHead/lastTail
 *
 *  排序时间复杂度 O(nlogn) + 一次线性扫描
 * @Author: chaoyi.zhang
 * @Date: 2020/10/12 13:45
 */
public class MergeInterval {

    public int[][] merge(int[][] intervals) {
        int rows = intervals.length;
        if(rows<=1){
            return intervals;
        }
        //先按第一列进行排序
        Arrays.sort(intervals, Comparator.comparingInt(eachArray -> eachArray[0]));

        List<Interval> list = new ArrayList<>();
        int lastHead = intervals[0][0];
        int lastTail = intervals[0][1];
        for(int r=1;r<rows;r++){
            int curHead = intervals[r][0];
            int curTail = intervals[r][1];

            if (lastTail < curHead) {
                //不重合进行记录上一个区间
                list.add(new Interval(lastHead, lastTail));

                lastHead = curHead;
                lastTail = curTail;

            } else {
                //重合区间 进行合并 边界判断， 进行更新 lastHead / lastTail
                if(lastTail < curTail){
                    lastTail = curTail;
                }
            }
            if(r==rows-1){
                list.add(new Interval(lastHead, lastTail));
            }
        }
        if (list.size()>0) {
            int[][] result = new int[list.size()][2];
            for(int i=0;i<list.size();i++){
                Interval interval = list.get(i);
                result[i][0] = interval.getHead();
                result[i][1] = interval.getTail();
            }
            print(result);
            return result;
        }

        return null;
    }

    private class Interval{
        int head;
        int tail;

        int getHead(){
            return head;
        }

        int getTail(){
            return tail;
        }

        Interval(int head, int tail){
            this.head = head;
            this.tail = tail;
        }
    }

    public static void print(int[][] array){
        if(array == null || array.length==0){
            return;
        }
        for(int r=0;r<array.length;r++){
            for(int c=0;c<array[0].length;c++){
                System.out.print(array[r][c]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        MergeInterval mi = new MergeInterval();
//        int[][] input = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        int[][] input = new int[][]{{2,3},{4,5},{6,7},{8,9},{1,10}};
//        int[][] input = new int[][]{{1,4}, {0,1}};

        mi.merge(input);
    }
}
