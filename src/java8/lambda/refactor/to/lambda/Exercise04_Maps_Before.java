package java8.lambda.refactor.to.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Counts word occurrences and groups orders by user.
 * TODOs:
 *  - Refactor BiFunction in merge(...) to a method reference (Integer::sum).
 *  - Refactor Function in computeIfAbsent(...) to a constructor reference (ArrayList::new).
 *  - Refactor Comparator for sorting to a method-reference-friendly comparator.
 */
public class Exercise04_Maps_Before {

  public static void main(String[] args) {
    // --- Word counts using merge ---
    List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "banana");
    Map<String, Integer> counts = new HashMap<>();
    for (String w : words) {
      // TODO #1: BiFunction -> method reference.
      counts.merge(w, 1, new BiFunction<Integer, Integer, Integer>() {
        @Override public Integer apply(Integer oldVal, Integer one) {
          return oldVal + one;
        }
      });
    }

    // --- Grouping orders by user using computeIfAbsent ---
    List<Order> orders = Arrays.asList(
        new Order("u-1", 120.0),
        new Order("u-2",  40.5),
        new Order("u-1",  99.9)
    );
    Map<String, List<Order>> byUser = new HashMap<>();
    for (Order o : orders) {
      // TODO #2: Function -> constructor reference.
      byUser.computeIfAbsent(o.getUserId(), new Function<String, List<Order>>() {
        @Override public List<Order> apply(String key) {
          return new ArrayList<>();
        }
      }).add(o);
    }

    // Print counts (sorted by value desc, then key asc).
    List<Map.Entry<String, Integer>> entries = new ArrayList<>(counts.entrySet());
    // TODO #3: Comparator -> method-reference-friendly comparator chain.
    entries.sort(new Comparator<Map.Entry<String, Integer>>() {
      @Override public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
        int r = b.getValue().compareTo(a.getValue());
        if (r == 0) r = a.getKey().compareTo(b.getKey());
        return r;
      }
    });

    System.out.println("=== Counts ===");
    for (Map.Entry<String, Integer> e : entries) {
      System.out.println(e.getKey() + " -> " + e.getValue());
    }

    System.out.println("\n=== Orders by user ===");
    for (Map.Entry<String, List<Order>> e : byUser.entrySet()) {
      System.out.println(e.getKey() + " -> " + e.getValue().size() + " orders");
    }
  }

  static final class Order {
    private final String userId;
    private final double amount;
    Order(String userId, double amount) { this.userId = userId; this.amount = amount; }
    String getUserId() { return userId; }
    double getAmount() { return amount; }
  }
}

