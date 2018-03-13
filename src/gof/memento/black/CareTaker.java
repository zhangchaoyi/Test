package gof.memento.black;

/**
 * Created by chris on 3/13/18.
 */
public class CareTaker {

    private MemotoIF memento;

    /**
     * 备忘录的取值方法
     */
    public MemotoIF retrieveMemento() {
        return this.memento;
    }

    /**
     * 备忘录的赋值方法
     */
    public void saveMemento(MemotoIF memento) {
        this.memento = memento;
    }

}
