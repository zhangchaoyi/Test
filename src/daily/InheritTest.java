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
        System.out.println("4--" + a2.show(b));//B and A   父类引用指向子类对象，父类引用只能访问父类的方法和覆盖的方法，此时实际上a2可见的方法有show(D obj); @Override show(A obj);
        System.out.println("5--" + a2.show(c));//B and A   同上
        System.out.println("6--" + a2.show(d));//A and D   就近当前对象原则？
        System.out.println("7--" + b.show(b));//B and B
        System.out.println("8--" + b.show(c));//B and B    就近父类原则？
        System.out.println("9--" + b.show(d));//A and D    就近当前对象原则？
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

class B extends A { //B是A的子类

    public String show(B obj){
        return "B and B";
    }

    @Override
    public String show(A obj){//override
        return "B and A";
    }

}

class C extends B {}//C是B的子类

class D extends B {}//D是B的子类
