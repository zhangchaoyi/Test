package gof.memento.wihte;

/**
 * Created by chris on 3/13/18.
 * 不破坏封闭的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。这样以后就可将该对象恢复到原先保存的状态
 */
public class MementoTest {

    public static void main(String[] args) {
        CareTaker c = new CareTaker();
        Originator o = new Originator();
        o.setState("state1");
        c.addMemento(o.newMemento());
        o.setState("state2");
        c.addMemento(o.newMemento());

        String state = o.getMementoState(c.getLastMemento());
        System.out.println(state);

        System.out.println(o.getMementoState(c.getLastMemento(0)));
    }
}
