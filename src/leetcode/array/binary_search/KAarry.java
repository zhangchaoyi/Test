package leetcode.array.binary_search;

/**
 * Created by chris on 6/24/18.
 * 一个有序数组向右偏移K位，K <= length, 求k
 * 如：10 28 40 50 1 4 5 6 8 9
 *
 * 一般解决办法的思路，遍历一次O(n), 直接寻找到k
 * 最优复杂度 二分 O(logn)
 *
 * k实际上就是找到array[0]
 * 使用 array[0]作为参照物，如果 array[n/2] > array[0] , 说明 k 位置在array[n/2]右侧
 *                        如果 array[n/2] < array[0] , 说明 k 位置在array[n/2]左侧
 */
public class KAarry {

    private int findK(int[] array){
        //判断是否没有发生移位
        if (array[0]<array[array.length-1]) {
            return 0;
        }

        int left = 0;
        int right = array.length - 1;
        int limit = array[0];

        while(left <= right){
            int mid = (left + right) / 2;
            if(array[mid]==limit){
                return 0;
            }
            if(array[mid] > limit){
                left = mid + 1;
            } else if(array[mid] < limit){
                right = mid - 1;
            }

            System.out.println("left:" + left + " right:" + right);
        }
        int end=left;
        //跟前一个比较，确定边界点
        if(array[left] < array[left-1]){
            end=left-1;
        }

        return end+1;
    }

    public static void main(String[] args){
//        int[] array = new int[]{10, 28, 30, 1, 4, 5, 6, 8, 9};
//        int[] array = new int[]{10, 28, 30, 40, 1, 4, 5, 6, 8, 9};
        int[] array = new int[]{4, 5, 6, 8, 9, 10, 28, 40, 50, 1, 2};
        KAarry k = new KAarry();
        System.out.println(k.findK(array));
    }
}
