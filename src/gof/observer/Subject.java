package gof.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcy on 18-3-14.
 */
public class Subject {
    List<Observer> observerList = new ArrayList<>();
    private int state;

    public void notifyAllObserver(){
        observerList.stream().forEach(o -> o.update());
    }

    public void addObserver(Observer o) {
        observerList.add(o);
    }

    public void setState(int state){
        this.state = state;
        notifyAllObserver();
    }

    public int getState(){
        return state;
    }
}
