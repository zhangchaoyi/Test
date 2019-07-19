package binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 多个数字找出相异的数字
 * @author: zhangchaoyi
 * @date: 2019/7/19
 */
public class FindDiffNum {

    public static void main(String[] args){
//        System.out.println(getOneDiffNum());
        System.out.println(getTwoDiffNum());
    }

    public static int getOneDiffNum(){
        Integer[] arr = new Integer[]{1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10};

        return getOneDiffNum(new ArrayList<>(Arrays.asList(arr)));
    }

    public static int getOneDiffNum(List<Integer> list){
        int result = 0;
        for(Integer i : list){
            result ^= i;
        }
        return result;
    }

    public static List<Integer> getTwoDiffNum(){
        Integer[] arr = new Integer[]{1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,11};

        int diffTwoNum = getOneDiffNum(new ArrayList<>(Arrays.asList(arr)));

        //此时diffTwoNum是两个相异数的异或结果
        int shiftBit = 0;
        while(shiftBit <= 31) {//这个循环是看从最低位开始，哪个位是第一个不同的，也就是为1的
            if(  (diffTwoNum & (1 << shiftBit)) != 0 ) break;
            shiftBit++;
        }

        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        for(Integer i : arr){
            if(  (i & (1 << shiftBit)) == 0  ) {
                a.add(i);
            } else {
                b.add(i);
            }
        }

        List<Integer> results = new ArrayList<>();
        results.add(getOneDiffNum(a));
        results.add(getOneDiffNum(b));
        return results;
    }
}
