package gof.singleton;

/**
 * Created by zcy on 18-3-15.
 */
public class LazySingleton {

    private LazySingleton(){}

    private static volatile LazySingleton lazySingleton = null;

    public static LazySingleton getLazySingleton(){
        if(lazySingleton == null){
            synchronized(LazySingleton.class) {
                if(lazySingleton == null){
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }
}

