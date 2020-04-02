package leetcode.common;

/**
 * Created by zcy on 18-5-17.
 */
public class SystemConvert {

    /**
     * 十进制转化为其他进制
     * @param tenSystemNumber
     * @param CN
     * @return
     */
    public static int convertSystem(int tenSystemNumber, int CN){
        boolean minus = false;
        if(tenSystemNumber < 0){
            minus = true;
            tenSystemNumber = -tenSystemNumber;
        }
        int quotient = tenSystemNumber;

        StringBuilder sb = new StringBuilder();
        while (quotient >= CN) {
            int reminder = quotient % CN;
            sb.append(reminder);
            quotient = quotient / CN;
        }
        sb.append(quotient % CN);
        int num = Integer.valueOf(sb.reverse().toString());
        if (minus) {
            num = -num;
        }
        return num;
    }

    public static void main(String[] args){
        System.out.print(convertSystem(-7, 7));
    }
}
