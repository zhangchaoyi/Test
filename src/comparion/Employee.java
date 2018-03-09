package comparion;

/**
 * Created by chris on 10/26/17.
 */
public class Employee implements Comparable<Employee> {
    private String name;
    private int age;
    private String country;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Employee(String name, int age, String country) {
        this.name = name;
        this.age = age;
        this.country = country;
    }

    @Override
    public int compareTo(Employee e) {
        if(this.getAge() - e.getAge() < 0){
            return -1;
        } else if(this.getAge() - e.getAge() == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
