package interfaces;

/**
 * Created by chris on 3/10/18.
 */
public class TestImpl2 extends TestImpl implements TestInterface {

    public static void main(String[] args){
        TestImpl2 t2 = new TestImpl2();

        t2.a();
        t2.b();
        t2.c();
        t2.d();
        t2.e();
        t2.f();
    }

    public void f(){
        System.out.println("TestImpl2 f()");
    }
}
