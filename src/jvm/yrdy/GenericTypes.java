package jvm.yrdy;

import java.util.List;

/**
 * Created by chris on 3/10/18.
 * 泛型遇见重载
 * 不能通过编译 List<String>和 List<Integer> 在编译之后都被擦除了 变成了原生的List<E>
 * 导致两个方法的签名一样
 */
public class GenericTypes {

//    public static void method(List<String> list){
//        System.out.println("invoke method(List<String> list)");
//    }
//
//    public static void method(List<Integer> list){
//        System.out.println("invoke method(List<String> list)");
//    }

//    public static String method(List<String> list){
//        System.out.println("invoke method(List<String> list)");
//        return "a";
//    }
//
//    public static int method(List<Integer> list){
//        System.out.println("invoke method(List<String> list)");
//        return 1;
//    }


    public static void main(String[] args){
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;

        System.out.println(c == d);//true
        System.out.println(e == f);//false
        System.out.println(c == (a + b));//true
        System.out.println(c.equals(a + b));//true
        System.out.println(g == (a + b));//true
        System.out.println(g.equals(a + b));//false

        System.out.println(c.equals(Integer.valueOf(3)));//true

    }
}
