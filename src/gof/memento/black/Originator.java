package gof.memento.black;

/**
 * Created by chris on 3/13/18.
 * 黑箱 通过将memento设置为private 且 Originator 的内部类控制由Originator存取
 */
public class Originator {

    private int state = 0;

    CareTaker caretaker = new CareTaker();

    public Memento creatMementoObject() {
        return new Memento(state);
    }

    /**
     * 将发起人恢复到备忘录对象所记载的状态
     */
    public void restoreMemento(MemotoIF momIf) {
        this.setState(((Memento) momIf).getState());
        System.out.println("黑箱恢复 备忘录 状态：" + state);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    private class Memento implements MemotoIF {

        private int state;

        public Memento(int state) {
            this.state = state;
            System.out.println("黑箱备忘录 当前保存 状态：" + state);
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {

            this.state = state;
        }

    }
}
