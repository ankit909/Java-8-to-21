package java8.lambda.refactor.to.lambda;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Exercise1Comparator_Before_Lambda {

  public static void main(String[] args) {
    List<Employee> employees = Arrays.asList(
        new Employee("Ava", "Nguyen", LocalDate.of(2021, 5, 12)),
        new Employee("Liam", "smith", LocalDate.of(2020, 7, 20)),
        new Employee("Maya", "Smith", LocalDate.of(2023, 1, 5)),
        new Employee("Eli", "Carter", LocalDate.of(2019, 3, 2))
    );

    // TODO #1: Refactor this anonymous Comparator into a lambda / method references.
    Collections.sort(employees, new Comparator<Employee>() {
      @Override
      public int compare(Employee a, Employee b) {
        int r = a.getLastName().compareToIgnoreCase(b.getLastName());
        if (r == 0) {
          r = a.getHireDate().compareTo(b.getHireDate());
        }
        return r;
      }
    });

    System.out.println("=== Sorted employees (last name, case-insensitive; then hire date) ===");
    for (Employee e : employees) {
      System.out.println(e);
    }
  }

  // Domain type for the exercise
  static class Employee {
    private final String firstName;
    private final String lastName;
    private final LocalDate hireDate;

    Employee(String firstName, String lastName, LocalDate hireDate) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.hireDate = hireDate;
    }

    String getFirstName() { return firstName; }
    String getLastName()  { return lastName; }
    LocalDate getHireDate() { return hireDate; }

    @Override public String toString() {
      return lastName + ", " + firstName + " (hired " + hireDate + ")";
    }
  }
}
