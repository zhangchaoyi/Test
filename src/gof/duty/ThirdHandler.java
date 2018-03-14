package gof.duty;

/**
 * Created by zcy on 18-3-14.
 */
public class ThirdHandler extends Handler {

    public ThirdHandler(int level){
        super.setLevel(level);
    }

    @Override
    protected void response() {
        System.out.println("success handler third");
    }

}