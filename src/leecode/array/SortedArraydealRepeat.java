package leecode.array;

/**
 * Created by chris on 5/13/18.
 * curNum用于记录不重复的记录位置
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
