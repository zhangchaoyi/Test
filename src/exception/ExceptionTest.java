package exception;

/**
 * Created by zcy on 18-3-22.
 * 保持异常栈的完整
 */
public class ExceptionTest {

    public static void main(String[] args) {
        A a = new A();
        try {
            a.f();
        } catch (MyException2 e) {
            e.printStackTrace();
        }
    }
}

class MyException1 extends Exception{

}


class MyException2 extends Exception{
    MyException2(Throwable throwable){
        super(throwable);
    }
    MyException2(){
        super();
    }
}

class A{
    public void f() throws MyException2{
        try {
            g();
        } catch (MyException1 e) {
            e.printStackTrace();
            //这里做了修改
            throw new MyException2(e);
        }
    }
    public void g() throws MyException1{
        throw new MyException1();
    }
}