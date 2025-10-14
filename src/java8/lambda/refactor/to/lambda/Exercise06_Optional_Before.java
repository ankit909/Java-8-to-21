package java8.lambda.refactor.to.lambda;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Demonstrates Optional transformations and actions.
 * TODOs:
 *  - Refactor map(Function) to a method reference.
 *  - Refactor orElseGet(Supplier) to a method/constructor reference.
 *  - Refactor ifPresent(Consumer) to a method reference.
 */
public class Exercise06_Optional_Before {

  public static void main(String[] args) {
    UserRepository repo = new UserRepository();
    Audit audit = new Audit();

    Optional<User> maybe = repo.findById("u-1");

    // TODO #1: Function -> method reference (e.g., User::getEmail).
    String email = maybe.map(new Function<User, String>() {
          @Override public String apply(User u) { return u.getEmail(); }
        })
        // TODO #2: Supplier -> method reference (class method or constructor).
        .orElseGet(new Supplier<String>() {
          @Override public String get() { return defaultEmail(); }
        });

    System.out.println("Resolved email: " + email);

    // TODO #3: Consumer -> bound method reference.
    maybe.ifPresent(new Consumer<User>() {
      @Override public void accept(User u) { audit.logUser(u); }
    });
  }

  static String defaultEmail() { return "noreply@example.com"; }

  // --- Infra ---
  static final class User {
    private final String id;
    private final String email;
    User(String id, String email) { this.id = id; this.email = email; }
    String getId() { return id; }
    String getEmail() { return email; }
  }

  static final class UserRepository {
    private final Map<String, User> db = new HashMap<>();
    UserRepository() {
      db.put("u-1", new User("u-1", "ava@example.com"));
      db.put("u-2", new User("u-2", "bob@example.com"));
    }
    Optional<User> findById(String id) {
      return Optional.ofNullable(db.get(id));
    }
  }

  static final class Audit {
    void logUser(User u) {
      System.out.println("Audit: user " + u.getId());
    }
  }
}
