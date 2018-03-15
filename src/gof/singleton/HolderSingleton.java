package gof.singleton;

/**
 * Created by zcy on 18-3-15.
 */
public class HolderSingleton {

    private HolderSingleton(){
        System.out.println("init");
    }

    private static class Holder {

        private static final HolderSingleton holderSingleton = new HolderSingleton();

    }

    public static HolderSingleton getSingleton(){
        return Holder.holderSingleton;
    }

    public static void main(String[] args){
//        getSingleton();//第一次访问时才初始化
    }
}
