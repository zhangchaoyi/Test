package gof.duty;

/**
 * Created by zcy on 18-3-14.
 * 责任链抽象 父类
 */
public abstract class Handler {

    protected Handler supervisor;
    private int level;

    public void setSupervisorHandler(Handler supervisor){
        this.supervisor = supervisor;
    }

    protected void handlerRequest(int i) {
        if(this.level >= i){
            response();
        } else {
            if(supervisor != null){
                supervisor.handlerRequest(i);
            }
            System.out.println("没有合适的handler");
        }
    }

    protected abstract void response();//子类自由覆盖

    protected void setLevel(int level){
        this.level = level;
    }
}
