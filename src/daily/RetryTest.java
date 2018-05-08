package daily;

/**
 * Created by zcy on 18-4-24.
 */
public class RetryTest {

    public static void main(String[] args) {
        testRequest();
    }

    public static void testRequest() {
        retry:// 1
        for (int i = 0; i < 10; i++) {
//            retry:// 2（行4）
            while (i == 5) {
                continue retry;
            }
            System.out.print(i + " ");
        }
    }
}
