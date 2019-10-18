package leecode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 返回两个字符串的最长公共子串
 *      str1 = "1A2345CD"; str2 = "12345EF"; 返回2345， 如果使用全遍历，先记录所有位置最长子串的长度，再找出最长子串的位置，
 *      再从该位置开始进行一次遍历， 则时间复杂度O(N * N * N + N + N)，空间复杂度：O(N)
 *
 *
 *      动态规划，使用二维矩阵 z[str1.length()][str2.length()]解决， 将str1作为纵坐标，str2作为横坐标， 只要判断 str1[i] == str2[j] 则可以认为 z[i][j] = 1,否则为0
 *      则只要找出最长的一条斜对角线即可；为了便于统计，可以使 z[i][j] = z[i-1][j-1] + 1 , 只需找到 z[][] 中最大的一项即可
 *
 *      z[i][j]的含义是把str[i]和str[j]当作公共子串最后一个字符的情况下，公共子串最长能多长；例如：str1="A1234B", str2="CD1234", dp[3][4]指把str1[3]和str2[4]
 *      当作公共子串的最后一个字符的情况下的公共子串长度，所以dp[3][4]=3; 如果对于dp[5][5]，因为str1[5]=B, str2[5]=4, 二者并不相等，不能构成公共子串，所以d[5][5]=0，
 *      
 *      
 *
 *      时间复杂度： O(M*N) + O(N), 使用二维数组遍历空间复杂度：O(M*N)； 使用斜线进行遍历空间复杂度O(1)
 *
 *      PS：二维矩阵中 z[i][j] 表示有i个数组，每个数组的长度为j，且 i 表示纵坐标， j 代表横坐标，所以二维矩阵每行代表一个数组
 *
 *
 *         1 2 3 4 5 E F
 *      1  1 0 0 0 0 0 0
 *      A  0 0 0 0 0 0 0
 *      2  0 1 0 0 0 0 0
 *      3  0 0 2 0 0 0 0
 *      4  0 0 0 3 0 0 0
 *      5  0 0 0 0 4 0 0
 *      C  0 0 0 0 0 0 0
 *      D  0 0 0 0 0 0 0
 *
 * @author: zhangchaoyi
 * @date: 2019/10/16
 */
public class LongestCommonSubstring {

    public static void main(String[] args) {
//        String str1 = "1A2345CD56789";
//        String str2 = "12345EF56780FSWCB";
        String str1 = "1A2345CD";
        String str2 = "12345EF";

        getCommonSubstring(str1, str2);

//        对于二维数组， z[i][j]  i是纵轴  j是横轴
//        int[][] matrix = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16},{17,18,19,20}};
//        System.out.println(matrix.length);
//        printArray(matrix);
    }

    /**
     * 经典动态规划方法 使用二维数组 空间复杂度O(M*N)
     * @param str1
     * @param str2
     * @return
     */
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



        printArray(z);



        return "";
    }

    /**
     * 找出最大值的纵坐标
     * @param z
     * @param max
     * @return
     */
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

    /**
     * 如果使用斜线进行遍历，则空间复杂度可以控制在 O(1)
     * @param str1
     * @param str2
     * @return
     */
//    public static String getCommonSubstring2(String str1, String str2) {
//
//    }
}
