package gof.memento.wihte;

/**
 * Created by chris on 3/13/18.
 * 负责存储Originator对象的内部状态，并可以防止Originator以外的其他对象访问备忘录。
 * 备忘录角色对任何对象都提供一个接口，即宽接口 是破坏封装性的
 */
public class Memento {
    private String state;

    public Memento(String state){this.state = state;}

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
