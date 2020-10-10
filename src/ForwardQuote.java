import java.util.LinkedHashMap;
import java.util.LinkedList;

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
        LinkedHashMap<String, String> l = new LinkedHashMap(10, 0.75f, true);
        l.put("1", "1");
        l.put("2", "2");
        System.out.println(l);
        l.get("1");

        System.out.println(l);
    }
}
