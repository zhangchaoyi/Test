package comparion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chris on 10/26/17.
 */
public class ComparableTest {

    public static void main(String[] args){
        comparion.Employee e1 = new comparion.Employee("a", 2, "a");
        comparion.Employee e2 = new comparion.Employee("b", 5, "b");
        comparion.Employee e3 = new comparion.Employee("c", 3, "c");
        comparion.Employee e4 = new comparion.Employee("d", 98, "d");

        List<comparion.Employee> employeeList = new ArrayList<comparion.Employee>(){
            {add(e1);}
            {add(e2);}
            {add(e3);}
            {add(e4);}
        };
        System.out.println("****** originlist:" + employeeList);
        Collections.sort(employeeList);
        System.out.println(employeeList);
    }




}
