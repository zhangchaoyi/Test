package gof.memento.wihte;

import gof.memento.wihte.Memento;

/**
 * Created by chris on 3/13/18.
 * 负责创建一个备忘录Memento，用以记录当前时刻自身的内部状态，并可使用备忘录恢复内部状态
 */
public class Originator {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memento newMemento(){
        return new Memento(state);
    }

    public String getMementoState(Memento m){
        return m.getState();
    }

}
