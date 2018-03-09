/**
 * Created by chris on 8/20/17.
 * java向前引用
 */
public class ForwardQuote {
    int method() {return n; }
    int m = method();
    int n = 1;

    public static void main(String[] args){
        ForwardQuote t = new ForwardQuote();
        System.out.println(t.n);
        System.out.println(t.m);

    }
}
