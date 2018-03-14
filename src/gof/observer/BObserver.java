package gof.observer;

/**
 * Created by zcy on 18-3-14.
 */
public class BObserver implements Observer {

    private Subject subject;

    public BObserver(Subject subject){
        this.subject = subject;
        subject.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("B****" + subject.getState());
    }
}
