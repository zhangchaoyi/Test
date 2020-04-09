package daily;

/**
 * @description: 测试自动装箱和自动拆箱， 在自动装箱时注意[-128, 127]优先使用cache，其余情况才进行new对象
 * @author: zhangchaoyi
 * @date: 2019/10/15
 */
public class IntegerAutoBoxing {

    public static void main(String[] args){
        // 1）基本类型和包装类型
        int a = 100;
        Integer b = 100;
        System.out.println(a == b);

        // 2）两个包装类型， 因为数值在 [-128, 127] 之间，所以在自动装箱时优先使用了IntegerCache，而非new对象；因此两个对象实际上一致
        Integer c = 100;
        Integer d = 100;
        System.out.println(c == d);

        // 3） 数值不在[-128, 127]之间，自动装箱通过new对象的形式
        c = 200;
        d = 200;
        System.out.println(c == d);
    }


}
