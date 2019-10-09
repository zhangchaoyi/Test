package daily;

/**
 * @description:
 *  obj.instanceof(class)
 *      1.一个对象是本身类的一个对象
 *      2.一个对象是本身类父类和接口的一个对象
 *      3.所有对象都是Object
 *      4.出现null都是false
 *
 *  isInstance测试， 代表这个对象能不能转化成这个类
 *      1.一个对象是本身类的一个对象
 *      2.一个对象是能被转化成本身类所继承类（父类）和实现接口的强转
 *      3.所有对象都能被Object强转
 *      4.出现null都是false
 *
 * @author: zhangchaoyi
 * @date: 2019/10/9
 */
public class InstanceTest {

    public static void main(String[] args){
        B b = new B();//子类
        A a = new A();//父类
        A ba = new B();//父类引用指向子类对象
        ba.print();

        System.out.println("1------------");
        System.out.println(a instanceof B);//false
        System.out.println(ba instanceof B);//true    父类引用对象实际上是子类对象，因此true
        System.out.println(ba instanceof A);//true
        System.out.println(b instanceof B);//true
        System.out.println(b instanceof A);//true
        System.out.println(b instanceof Object);//true
        System.out.println(null instanceof Object);//false
        System.out.println("2------------");
        System.out.println(b.getClass().isInstance(b));//true
        System.out.println(b.getClass().isInstance(a));//false  父类对象不能强转为子类类型
        System.out.println("3------------");
        System.out.println(a.getClass().isInstance(ba));//true
        System.out.println(b.getClass().isInstance(ba));//true   父类引用对象实际上是子类对象，因此true
        System.out.println(b.getClass().isInstance(null));//false
        System.out.println("4------------");
        System.out.println(A.class.isInstance(a));//true
        System.out.println(A.class.isInstance(b));//true
        System.out.println(A.class.isInstance(ba));//true
        System.out.println("5------------");
        System.out.println(B.class.isInstance(a));//false
        System.out.println(B.class.isInstance(b));//true
        System.out.println(B.class.isInstance(ba));//true  父类引用对象实际上是子类对象，因此true
        System.out.println("6------------");
        System.out.println(Object.class.isInstance(b));//true
        System.out.println(Object.class.isInstance(null));//false
    }

    static class A {

        public void print(){
            System.out.println("A");
        }

    }

    static class B extends A {

        @Override
        public void print(){
            System.out.println("B");
        }

    }
}
