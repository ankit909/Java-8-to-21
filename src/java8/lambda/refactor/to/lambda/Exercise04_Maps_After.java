package java8.lambda.refactor.to.lambda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise04_Maps_After {
  public static void main(String[] args) {
    // --- Word counts using merge ---
    List<String> words = List.of("apple", "banana", "apple", "orange", "banana", "banana");
    Map<String, Integer> counts = new HashMap<>();
    for (String w : words) {
      counts.merge(w, 1, Integer::sum);
    }
    List<Order> orders = List.of(
        new Order("u-1", 120.0),
        new Order("u-2",  40.5),
        new Order("u-1",  99.9)
    );
    Map<String, List<Order>> byUser = new HashMap<>();
    for (Order o : orders) {
      byUser.computeIfAbsent(o.getUserId(), key -> new ArrayList<>()).add(o);
    }

    // Print counts (sorted by value desc, then key asc).
    List<Map.Entry<String, Integer>> entries = new ArrayList<>(counts.entrySet());
    entries.sort((a, b) -> {
      int r = b.getValue().compareTo(a.getValue());
      return r != 0 ? r : a.getKey().compareTo(b.getKey());
    });

    System.out.println("=== Counts ===");
    entries.forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));

    System.out.println("\n=== Orders by user ===");
    byUser.forEach((user, orderList) ->
        System.out.println(user + " -> " + orderList.size() + " orders")
    );
  }

  static final class Order {
    private final String userId;
    private final double amount;
    Order(String userId, double amount) { this.userId = userId; this.amount = amount; }
    String getUserId() { return userId; }
    double getAmount() { return amount; }
  }
}
