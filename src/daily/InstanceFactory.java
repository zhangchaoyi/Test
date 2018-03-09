package daily;

/**
 * Created by chris on 3/3/18.
 * 类初始化加载
 */
public class InstanceFactory {
    private static class InstanceHolder{
        public static Instance instance = new Instance();
    }

    public static Instance getInstance(){
        return InstanceHolder.instance;
    }

    public static void main(String[] args){
        System.out.println(InstanceFactory.getInstance());
    }
}

class Instance{
    private static final int a = 1;
    static {
       System.out.println(a);
    }
    Instance(){System.out.println("init");}
}
