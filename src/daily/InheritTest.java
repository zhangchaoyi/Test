package daily;

/**
 * Created by zcy on 18-3-20.
 * 继承对象的最近原则限制
 */
public class InheritTest {

    public static void main(String[] args){
        A a1 = new A();
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();

        System.out.println("1--" + a1.show(b));//A and A r
        System.out.println("2--" + a1.show(c));//A and A r
        System.out.println("3--" + a1.show(d));//A and D r
        System.out.println("4--" + a2.show(b));//B and B w B and A   //父类引用指向子类对象 ，父类引用只能访问父类的方法
        System.out.println("5--" + a2.show(c));//B and A
        System.out.println("6--" + a2.show(d));//B and A w A and D
        System.out.println("7--" + b.show(b));//B and B
        System.out.println("8--" + b.show(c));//B and A w B and B
        System.out.println("9--" + b.show(d));//B and A w A and D
    }


}


class A {
    public String show(D obj){
        return "A and D";
    }

    public String show(A obj){
        return "A and A";
    }
}

class B extends A {
    public String show(B obj){
        return "B and B";
    }

    public String show(A obj){//override
        return "B and A";
    }
}

class C extends B {}

class D extends B {}