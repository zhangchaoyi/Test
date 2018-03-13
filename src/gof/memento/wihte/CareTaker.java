package gof.memento.wihte;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 3/13/18.
 * 负责备忘录Memento，不能对Memento的内容进行访问或者操作。
 */
public class CareTaker {
    private List<Memento> mementoList = new ArrayList<>();

    public void addMemento(Memento m){
        mementoList.add(m);
    }

    public Memento getLastMemento(){
        if(mementoList.size() == 0){
            return null;
        }
        return mementoList.remove(mementoList.size() - 1);
    }

    public Memento getLastMemento(int i){
        if(i > mementoList.size() - 1){
            return null;
        }

        return mementoList.remove(mementoList.size() - 1);
    }


}
