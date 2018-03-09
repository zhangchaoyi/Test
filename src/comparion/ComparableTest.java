package comparion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chris on 10/26/17.
 */
public class ComparableTest {

    public static void main(String[] args){
        Employee e1 = new Employee("a", 2, "a");
        Employee e2 = new Employee("b", 5, "b");
        Employee e3 = new Employee("c", 3, "c");
        Employee e4 = new Employee("d", 98, "d");

        List<Employee> employeeList = new ArrayList<Employee>(){
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
