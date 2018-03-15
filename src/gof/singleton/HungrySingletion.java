package gof.singleton;

/**
 * Created by zcy on 18-3-15.
 */
public class HungrySingletion {

    private HungrySingletion(){System.out.println("init");}

    private static final HungrySingletion hungrySingleton = new HungrySingletion();

    public static HungrySingletion getSingletion(){
        return hungrySingleton;
    }

    public static void main(String[] args){
        //加载阶段即发生初始化
    }
}
