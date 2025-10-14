package java8.lambda.refactor.to.lambda;

import java.util.List;

public class Exercise03_StreamPipeline_After {
  public static void main(String[] args) {
    List<Customer> customers = List.of(
        new Customer(1L, "ava@example.com", true),
        new Customer(2L, "bob@example.com", false),
        new Customer(3L, "zoe@example.com", true),
        new Customer(4L, "ALICE@example.com", true)
    );

    customers.stream()
        .filter(Customer::isActive)
        .map(Customer::getEmail)
        .distinct()
        .sorted(String::compareToIgnoreCase)
        .forEach(System.out::println);
  }

  // --- Domain type ---
  static final class Customer {
    private final long id;
    private final String email;
    private final boolean active;

    Customer(long id, String email, boolean active) {
      this.id = id; this.email = email; this.active = active;
    }
    long getId() { return id; }
    String getEmail() { return email; }
    boolean isActive() { return active; }
  }
}
