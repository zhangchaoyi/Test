package gof.observer;

/**
 * Created by zcy on 18-3-14.
 */
public class ObserverTest {

    public static void main(String[] args) {
        Subject subject = new Subject();
        AObserver aObserver = new AObserver(subject);
        BObserver bObserver = new BObserver(subject);
        CObserver cObserver = new CObserver(subject);

        subject.setState(1);
        subject.setState(2);

    }
}
