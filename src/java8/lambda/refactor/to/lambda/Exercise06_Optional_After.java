package java8.lambda.refactor.to.lambda;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Demonstrates Optional transformations and actions.
 * All transformations now use method/constructor references for clarity and conciseness.
 */
public class Exercise06_Optional_After {

  public static void main(String[] args) {
    UserRepository repo = new UserRepository();
    Audit audit = new Audit();

    Optional<User> maybe = repo.findById("u-1");

    String email = maybe.map(User::getEmail)
        .orElseGet(Exercise06_Optional_After::defaultEmail);

    System.out.println("Resolved email: " + email);

    maybe.ifPresent(audit::logUser);
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
