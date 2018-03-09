package reflect;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by chris on 3/5/18.
 */
public class ReflectTest {

    public static void main(String[] args){
        Class<?> cl = null;
        try{
            cl = Class.forName(args[0]);
        } catch (ClassNotFoundException e){
            System.err.println("Class not found");
            System.exit(1);
        }

        System.out.println(cl.getCanonicalName());

        Set<String> s = null;
        try{
            s = (Set<String>) cl.newInstance();
        } catch(IllegalAccessException e){
            System.err.println("Class not accessible");
            System.exit(1);
        } catch (InstantiationException e){
            System.err.println("Class not instantiable");
            System.exit(1);
        }

        s.addAll(Arrays.asList(args).subList(1, args.length));
        System.out.println(s);
    }
}
