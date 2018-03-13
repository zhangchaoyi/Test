package gof.memento.black;

/**
 * Created by chris on 3/13/18.
 */
public class Client {

    public static void main(String[] args) {

        int state = 3;
        Originator originator = new Originator();
        CareTaker caretaker = new CareTaker();

        originator.setState(state);
        /**
         * 创建备忘录对象的 缓存起来
         */
        caretaker.saveMemento(originator.creatMementoObject());
        /*
         * 进行设置重新还原
         */
        originator.setState(5);
        System.out.println(" 黑箱发起人更改状态：" + originator.getState());

        originator.restoreMemento(caretaker.retrieveMemento());

    }
}
