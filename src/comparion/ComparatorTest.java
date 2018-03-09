package comparion;

import java.util.*;

/**
 * Created by chris on 10/26/17.
 */
public class ComparatorTest {

    public static void main(String[] args){
        Apple a = new Apple("a", 1, "red");
        Apple b = new Apple("b", 5, "yellow");
        Apple c = new Apple("c", 3, "green");
        Apple d = new Apple("d", 7, "blue");
        List<Apple> appleList = new ArrayList<Apple>(){{
            add(a);
            add(b);
            add(c);
            add(d);
        }};
        System.out.println("*** original list:" + appleList);
        Collections.sort(appleList, Comparator.comparingInt(Apple::getWeight));
        System.out.println(appleList);
    }
    /*
    new Comparator<Apple>(){

     @Override
     public int compare(Apple o1, Apple o2){
        return o1.getWeight() - o2.getWeight();
     }
    }
    */

    /*
    (Apple o1, Apple o2)->  o1.getWeight() - o2.getWeight()
     */
}
