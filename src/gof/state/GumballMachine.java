package gof.state;

/**
 * https://www.runoob.com/w3cnote/state-vs-strategy.html
 * 实现一个糖果机
 * 糖果机分为 1、没有投币状态  2、有投币状态   3、出售状态   4、售罄状态
 *
 * 初始化是状态1，在投币之后变成状态2
 * 状态2在扭转后变成状态3
 * 状态3判断当前糖果数量，如果有则变成状态1；如果糖果没有则变成状态4
 *
 * 跟策略模式有点类似，区别是策略模式是直接一次执行
 * 状态模式是Context类中定义的组合状态之间内部可以直接转换
 */
public class GumballMachine extends State{
    public State noQuarterState = new NoQuarterState(this);
    public State hasQuarterState = new HasQuarterState(this);
    public State soldState = new SoldState(this);
    public State soldOutState = new SoldOutState(this);

    private State state = soldOutState;
    private int candyCount = 0;

    public GumballMachine(int count) {
        this.candyCount = count;
        if(count > 0)
            setState(noQuarterState);
    }

    @Override
    public void insertQuarter() {
        state.insertQuarter();
    }
    @Override
    public void ejectQuarter() {
        state.ejectQuarter();
    }
    @Override
    public void turnCrank() {
        state.turnCrank();
    }
    @Override
    public void dispense() {
        state.dispense();
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setCandyCount(int candyCount) {
        this.candyCount = candyCount;
    }

    public int getCandyCount() {
        return candyCount;
    }

}
