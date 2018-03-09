package gof.strategy;

import gof.factory.ComponentA;
import gof.factory.ComponentB;
import gof.factory.FactortInterface;

/**
 * Created by chris on 10/15/17.
 */
public class StrategyContext {
    private FactortInterface factortInterface;

    public StrategyContext(FactortInterface factortInterface){
        this.factortInterface = factortInterface;
    }

    public void doSomething(){
        factortInterface.doSomething();
    }

    public static void main(String[] args){
        FactortInterface fiA = new ComponentA();
        StrategyContext scA = new StrategyContext(fiA);
        scA.doSomething();

        System.out.println("-------------------------------------");

        FactortInterface fiB = new ComponentB();
        StrategyContext scB = new StrategyContext(fiB);
        scB.doSomething();
    }
}
