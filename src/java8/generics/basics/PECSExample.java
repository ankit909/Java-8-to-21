package java8.generics.basics;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//PECS - Producer extends, Consumer supplies
class Person {
    final String name;
    Person(String name) {
        this.name = name;
    }
}

class Employee extends Person {
    Employee(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return name;
    }
}

class Manager extends Employee {
    Manager(String name) {
        super(name);
    }
}

public class PECSExample {

    public static <T> void copy(Collection<? super T> dest, Collection<? extends T> src) {
        dest.addAll(src);
    }

    public static void main(String[] args) {
        List<Manager> managers = List.of(new Manager("Alice (Mgr)"), new Manager("Bob (Mgr)"));
        List<Person> peopleDirectory =  new ArrayList<>();
        copy(peopleDirectory, managers);
        System.out.println(peopleDirectory);

        List<Object> everything = new ArrayList<>();
        List<Employee> employees = List.of(new Employee("Richard (Emp)"), new Employee("John (Emp)"));
        copy(everything, employees);
        System.out.println(everything);
    }
}
