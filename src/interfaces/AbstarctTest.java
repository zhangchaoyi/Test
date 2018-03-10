package interfaces;

/**
 * Created by chris on 3/10/18.
 */
public abstract class AbstarctTest implements TestInterface {

    @Override
    public void a(){
        System.out.println("abstractTest a()");
    }

    @Override
    public void b(){
        System.out.println("abstractTest b()");
    }

    @Override
    public void c(){
        System.out.println("abstractTest c()");
    }

}
