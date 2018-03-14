package gof.duty;

/**
 * Created by zcy on 18-3-14.
 */
public class HandlerTest {

    public static void main(String[] args) {
        Handler firstHandler = new FirstHandler(1);
        Handler secondHandler = new SecondHandler(10);
        Handler thirdHandler = new ThirdHandler(100);

        firstHandler.setSupervisorHandler(secondHandler);
        secondHandler.setSupervisorHandler(thirdHandler);


        firstHandler.handlerRequest(8);
    }
}
