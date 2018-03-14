package gof.observer;

/**
 * Created by zcy on 18-3-14.
 */
public class CObserver implements Observer {

    private Subject subject;

    public CObserver(Subject subject){
        this.subject = subject;
        subject.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("C****" + subject.getState());
    }
}
