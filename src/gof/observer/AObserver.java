package gof.observer;

/**
 * Created by zcy on 18-3-14.
 */
public class AObserver implements Observer {

    private Subject subject;

    public AObserver(Subject subject){
        this.subject = subject;
        subject.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("A****" + subject.getState());
    }
}
