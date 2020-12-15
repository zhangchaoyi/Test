package daily;

/**
 * @Author: chaoyi.zhang
 * @Date: 2020/12/15 16:04
 */
public class StringTest {

    public static void main(String[] args){
        String s0="kvill";
        String s1="kvill";
        String s2="kv" + "ill";
        String s3 = new String("kvill");

        System.out.println( s0==s1 );// s0 和 s1 都是常量池中的字符串value[]常量引用，因此比较引用相等
        System.out.println( s0==s2 );// 实际上s2完成拼接后在常量池就是"kvill"
        System.out.println( s0==s3 );//s3是一个堆空间的对象地址
        System.out.println( s0==s3.intern() );//intern()方法可以获取常量池中的常量引用


    }
}
