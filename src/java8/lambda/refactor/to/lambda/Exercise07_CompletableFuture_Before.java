package java8.lambda.refactor.to.lambda;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * End-to-end async pipeline: load -> transform -> deliver.
 * TODOs:
 *  - Refactor Supplier, Function, Consumer to method references.
 *  - Keep exceptionally(...) but consider a readable method reference if you extract a handler.
 */
public class Exercise07_CompletableFuture_Before {

  public static void main(String[] args) {
    OrderService service = new OrderService();
    ExecutorService pool = Executors.newFixedThreadPool(2);

    CompletableFuture<Void> pipeline =
        CompletableFuture.supplyAsync(new Supplier<Order>() {
              @Override public Order get() { return service.loadOrder("o-1001"); }
            }, pool)
            .thenApply(new Function<Order, Receipt>() {
              @Override public Receipt apply(Order order) { return service.toReceipt(order); }
            })
            .thenAccept(new Consumer<Receipt>() {
              @Override public void accept(Receipt receipt) { service.sendReceipt(receipt); }
            })
            .exceptionally(new Function<Throwable, Void>() {
              @Override public Void apply(Throwable t) {
                System.err.println("Error: " + t.getMessage());
                return null;
              }
            });

    pipeline.join();
    pool.shutdown();
  }

  // --- Domain/service ---
  static final class OrderService {
    Order loadOrder(String id) {
      // Simulate I/O
      sleep(150);
      return new Order(id, 199.99);
    }
    Receipt toReceipt(Order order) {
      sleep(50);
      return new Receipt("R-" + order.getId(), order.getTotal());
    }
    void sendReceipt(Receipt r) {
      System.out.println("Sent " + r.getId() + " for $" + r.getAmount());
    }
    private static void sleep(long ms) {
      try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
  }
  static final class Order {
    private final String id; private final double total;
    Order(String id, double total) { this.id = id; this.total = total; }
    String getId() { return id; } double getTotal() { return total; }
  }
  static final class Receipt {
    private final String id; private final double amount;
    Receipt(String id, double amount) { this.id = id; this.amount = amount; }
    String getId() { return id; } double getAmount() { return amount; }
  }
}
