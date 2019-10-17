package leecode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 返回两个字符串的最长公共子串
 *      str1 = "1A2345CD"; str2 = "12345EF"; 返回2345， 如果使用全遍历，先记录所有位置最长子串的长度，再找出最长子串的位置，
 *      再从该位置开始进行一次遍历， 则时间复杂度O(N * N * N + N + N)，空间复杂度：O(N)
 *
 *
 *      动态规划，使用二维矩阵 z[][]解决， 将str1作为横坐标，str2作为纵坐标， 只要判断 str1[i] == str2[j] 则可以认为 z[i][j] = 1,
 *      则只要找出最长的一条斜对角线即可；为了便于统计，可以使 z[i][j] = z[i-1][j-1] + 1 , 只需找到 z[][] 中最大的一项即可
 *
 *      PS：二维矩阵中 z[i][j] 表示有i个数组，每个数组的长度为j，且 i 表示纵坐标， j 代表横坐标，所以二维矩阵每行代表一个数组
 *
 * @author: zhangchaoyi
 * @date: 2019/10/16
 */
public class LongestCommonSubstring {

    public static void main(String[] args) {
        String str1 = "1A2345CD56789";
        String str2 = "12345EF56780";

        getCommonSubstring(str1, str2);

//        对于二维数组， z[i][j]  i是纵轴  j是横轴
//        int[][] matrix = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16},{17,18,19,20}};
//        System.out.println(matrix.length);
//        printArray(matrix);
    }

    public static String getCommonSubstring(String str1, String str2){
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] z = new int[str1.length()][str2.length()];

        int tempMax = 0;
        for(int i=0; i<chs1.length; i++) {
            for(int j=0; j<chs2.length; j++){
                if(chs1[i] == chs2[j]){
                    z[i][j] = (i==0 || j==0) ? 1 : (z[i-1][j-1]+1);
                    if (z[i][j] > tempMax) {
                        tempMax = z[i][j];
                    }
                }
            }
        }

        List<Integer> indexStr1 = getMaxValueStr1CorList(z, tempMax);
        for(Integer i : indexStr1){
            System.out.println(str1.substring(i-tempMax+1, i+1));
        }



//        printArray(z);



        return "";
    }

    public static List<Integer> getMaxValueStr1CorList(int[][] z, int max){
        List<Integer> l = new ArrayList<>();
        for(int i=0;i<z.length;i++){
            for(int j=0;j<z[i].length;j++){
                if (z[i][j] == max) {
                    l.add(i);
                }
            }
        }
        return l;
    }

    public static void printArray(int[][] z){
        for(int i=0;i<z.length;i++){
            for(int j=0;j<z[i].length;j++){
                System.out.print(z[i][j] + " ");
            }
            System.out.println();
        }
    }
}
