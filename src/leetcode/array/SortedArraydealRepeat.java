package leetcode.array;

/**
 * Created by chris on 5/13/18.
 * 从排序数组中删除重复项
 *给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

 示例1
 给定数组 nums = [1,1,2],

 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。

 你不需要考虑数组中超出新长度后面的元素。


 示例2
 给定 nums = [0,0,1,1,1,2,2,3,3,4],

 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。

 你不需要考虑数组中超出新长度后面的元素。

 最优复杂度 O(n) 保持空间为当前的O(1)
 *
 * curNum用于记录不重复的记录位置, 即使用当前的前半段数组进行存放不重复的数字
 */
class SortedArraydealRepeat {
    public static int removeDuplicates(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int curNum = 0;//不重复的当前索引
        for(int i=1; i<nums.length; i++){
            if(nums[curNum] != nums[i]){
                nums[++curNum] = nums[i];
            }
        }

        return curNum+1;
    }

    public static void main(String[] args){
        int[] a = new int[]{0, 0, 1, 1, 1, 2, 3,4,5};

        System.out.println(SortedArraydealRepeat.removeDuplicates(a));

        for(int t:a){
            System.out.print(t + " ");
        }

    }
}
