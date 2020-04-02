package leetcode.array;

/**
 * Created by chris on 6/24/18.
 * 一个有序数组向右偏移K位，K <= length, 求k
 * 如：10 28 40 50 1 4 5 6 8 9
 *
 * 一般解决办法的思路，遍历一次O(n), 直接寻找到k
 * 最优复杂度 二分 O(logn)
 */
public class KAarry {

    private int findK(int[] array){
        int left = 0;
        int right = array.length - 1;
        int limit = array[0];

        while(left < right){
            int mid = (left + right) / 2;
            if(array[mid] >= limit){
                left = mid + 1;
            } else if(array[mid] < limit){
                right = mid - 1;
            }

            System.out.println("left:" + left + " right:" + right);
        }

        if(left == right){
            return 0;
        }
        return left + 1;
    }

    public static void main(String[] args){
//        int[] array = new int[]{10, 28, 40, 50, 1, 4, 5, 6, 8, 9};
        int[] array = new int[]{1, 4, 5, 6, 8, 9,10, 28, 40, 50};
        KAarry k = new KAarry();
        System.out.println(k.findK(array));
    }
}
