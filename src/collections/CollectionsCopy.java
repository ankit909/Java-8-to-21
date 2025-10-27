package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

public class CollectionsCopy {

    public static void main(String[] args) {
        List<Manager> managers = List.of(new Manager("Carol (Mgr)"), new Manager("Dave (Mgr)"));

// Pre-size dest using placeholders (size must match or exceed src)
        List<Person> preSized = new ArrayList<>(Collections.nCopies(managers.size(), new Person("(placeholder)")));

        Collections.copy(preSized, managers); // compiles due to both bounds
        System.out.println("Pre-sized copy: " + preSized);

    }
}
