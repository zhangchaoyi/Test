package comparion;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by chris on 10/26/17.
 */
public class ComparableTest {

    public static void main(String[] args){
        Employee e1 = new Employee("a", 2, "a", Business.BANK);
        Employee e2 = new Employee("b", 5, "b", Business.BANK);
        Employee e3 = new Employee("c", 3, "c", Business.HOSPITAL);
        Employee e4 = new Employee("hello", 98, "d", Business.BANK);
        Employee e5 = new Employee("c", 3, "c", Business.BANK);

        List<Employee> employeeList = new ArrayList<Employee>(){
            {add(e1);}
            {add(e2);}
            {add(e3);}
            {add(e4);}
            {add(e5);}
        };
//        System.out.println("****** originlist:" + employeeList);
//        Collections.sort(employeeList);
//        System.out.println(employeeList);

        List<String> list = Arrays.asList("hello", "world");

//        System.out.println(employeeList.stream().anyMatch(_e -> list.contains(_e.getName())));

        //list 中根据某个属性排序
        List<Employee> l = employeeList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Employee::getBusiness))),
                ArrayList::new));
        System.out.println(l);
    }




}
