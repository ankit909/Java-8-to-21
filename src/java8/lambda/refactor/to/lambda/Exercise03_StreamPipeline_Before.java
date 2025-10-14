package java8.lambda.refactor.to.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Builds a stream pipeline to extract and print active customer emails.
 * TODOs:
 *  - Replace Predicate, Function, Consumer anonymous classes with method references.
 *  - Replace Comparator with a method-reference-friendly comparator.
 */
public class Exercise03_StreamPipeline_Before {

  public static void main(String[] args) {
    List<Customer> customers = Arrays.asList(
        new Customer(1L, "ava@example.com", true),
        new Customer(2L, "bob@example.com", false),
        new Customer(3L, "zoe@example.com", true),
        new Customer(4L, "ALICE@example.com", true)
    );

    // TODO #1: Predicate -> method reference (unbound instance).
    List<String> activeEmails = customers.stream()
        .filter(new Predicate<Customer>() {
          @Override public boolean test(Customer c) { return c.isActive(); }
        })
        // TODO #2: Function -> method reference.
        .map(new Function<Customer, String>() {
          @Override public String apply(Customer c) { return c.getEmail(); }
        })
        .distinct()
        // TODO #3: Comparator -> method-reference-friendly comparator (case-insensitive).
        .sorted(new Comparator<String>() {
          @Override public int compare(String a, String b) {
            return a.compareToIgnoreCase(b);
          }
        })
        .collect(Collectors.toList());

    // TODO #4: Consumer -> method reference.
    activeEmails.forEach(new Consumer<String>() {
      @Override public void accept(String s) {
        System.out.println(s);
      }
    });
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

