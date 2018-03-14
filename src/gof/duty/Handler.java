package gof.duty;

/**
 * Created by zcy on 18-3-14.
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
        }
    }

    protected abstract void response();

    protected void setLevel(int level){
        this.level = level;
    }
}
