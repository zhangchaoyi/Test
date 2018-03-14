package gof.duty;

/**
 * Created by zcy on 18-3-14.
 */
public class FirstHandler extends Handler {

    public FirstHandler(int level){
        super.setLevel(level);
    }

    @Override
    protected void response() {
        System.out.println("success handler first");
    }
}
