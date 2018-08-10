package lambda;

import lambda.bean.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: zhangchaoyi
 * @date: 2018/8/9
 */
public class Java8Test {

    public static void main(String[] args) {
        List<Person> roster = initData();

        System.out.println("list:" +roster);

        double average = roster.stream().filter(p -> p.getGender() == Person.Sex.MALE).mapToInt(Person::getAge).average().getAsDouble();
        System.out.printf("average:%s", average);

        //sum 使用sum() 实际就是reduce
        int ageSum = roster.stream().mapToInt(Person::getAge).sum();
        int ageSum2 = roster.stream().map(Person::getAge).reduce(0, Integer::sum);
        //使用collect进行sun
        int ageSum3 = roster.stream().collect(Collectors.summingInt(Person::getAge)).intValue();
        System.out.printf("sum1:%s - sum2:%s - sum3:%s \n", ageSum, ageSum2, ageSum3);

        //求各个性别的年龄总数
        Map<Person.Sex, Integer> ageSumByGender = roster.stream().collect(Collectors.groupingBy(Person::getGender, Collectors.summingInt(Person::getAge)));

        //求各个年龄的年龄平均数
        Map<Person.Sex, Double> ageAveByGender = roster.stream().collect(Collectors.groupingBy(Person::getGender, Collectors.averagingDouble(Person::getAge)));

        System.out.printf("ageSumByGender:%s - ageAveByGender:%s", ageSumByGender, ageAveByGender);

        //使用parallelStream
        ConcurrentMap<Person.Sex, List<Person>> m = roster.parallelStream().collect(Collectors.groupingByConcurrent(Person::getGender));

        System.out.println("serial stream:");
        roster.stream().forEach(p -> System.out.println(p));
        System.out.println("parallel stream:");
        roster.parallelStream().forEach(p -> System.out.println(p));
        System.out.println("parallel stream with forEachOrdered:");
        roster.parallelStream().forEachOrdered(p -> System.out.println(p));
    }

    private static List<Person> initData(){
        List<Person> list = new ArrayList<>();
        for(int i=0; i<10; i++){
            Person.Sex gender = i % 4 == 0 ? Person.Sex.MALE : Person.Sex.FEMALE;
            int age = i % 20;
            list.add(new Person("person" + i, gender, age));
        }

        return list;
    }
}
