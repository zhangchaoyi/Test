package interfaces;

/**
 * Created by chris on 3/10/18.
 */
public class TestImpl extends AbstarctTest implements TestInterface {

    public static void main(String[] args){
        TestImpl ti = new TestImpl();
        ti.a();
        ti.b();
        ti.c();
        ti.d();
        ti.e();
        ti.f();

    }

    public void c(){
        System.out.println("TestImpl c()");
    }

    public void d(){
        System.out.println("TestImpl d()");
    }

    public void e(){
        System.out.println("TestImpl e()");
    }

    public void f(){
        System.out.println("TestImpl f()");
    }
}
