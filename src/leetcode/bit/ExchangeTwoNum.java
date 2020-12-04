package leetcode.bit;

/**
 * @description:
 * @author: zhangchaoyi
 * @date: 2019/7/22
 */
public class ExchangeTwoNum {

    public static void main(String[] args){
        int x = 1; int y = 2;

        System.out.println("x:"+ x + " y:" + y);
        x = x^y;
        y = x^y;//(x^y)^y = x^(y^y) = x^0 = x;
        x = x^y;//(x^y)^x = (x^x)^y = 0^y = y;

        System.out.println("x:"+ x + " y:" + y);
    }
}
