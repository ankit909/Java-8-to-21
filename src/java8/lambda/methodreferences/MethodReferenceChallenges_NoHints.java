package java8.lambda.methodreferences;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class MethodReferenceChallenges_NoHints {

  public static void main(String[] args) throws Exception {
    challenge1();
    challenge2();
    challenge3();
    challenge4();
    challenge5();
    challenge6();
    challenge7();
    challenge8();
    challenge9();
    challenge10();
  }

  private static void challenge1() throws IOException {
    System.out.println("\n[1] Challenge");
    // Use direct method references, no need to assign to variables
    Path temp = Files.createTempFile("mr", ".tmp");
    System.out.println("parse('42')=" + Integer.parseInt("42"));
    System.out.println("max(3,7)=" + Math.max(3, 7));
    System.out.println("exists(temp)=" + Files.exists(temp));
    System.out.println("abs(-9)=" + Math.abs(-9));
  }

  private static void challenge2() {
    System.out.println("\n[2] Challenge");
    Service svc = new Service();
    Map<String, Integer> cache = new LinkedHashMap<>(Map.of("x", 1, "y", 2));
    svc.flush();
    System.out.println("cacheGet('x')=" + cache.get("x"));
  }

  private static void challenge3() {
    System.out.println("\n[3] Challenge");
    List<String> names = new ArrayList<>(List.of("zoe", "ALICE", "bob"));
    names.sort(String::compareToIgnoreCase);
    System.out.println("sorted: " + names);
    System.out.println("length('abc')=" + "abc".length());
    System.out.println("startsWith('foobar','foo')=" + "foobar".startsWith("foo"));
    System.out.println("contains('abracadabra','cad')=" + "abracadabra".contains("cad"));
  }

  private static void challenge4() {
    System.out.println("\n[4] Challenge");
    List<String> list = new ArrayList<>();
    list.add("ok");
    System.out.println("list=" + list);
    System.out.println("big=" + new BigInteger("12345678901234567890"));
    System.out.println("point=" + new Point2(3, 4));
  }

  private static void challenge5() {
    System.out.println("\n[5] Challenge");
    String[] arr = List.of("a", "bb", "ccc").toArray(new String[0]);
    System.out.println("makeArray(3).length=" + new String[3].length);
    System.out.println("arr[1]=" + arr[1]);
  }

  private static void challenge6() {
    System.out.println("\n[6] Challenge");
    List<User> users = List.of(
        new User("u1", "ava@example.com"),
        new User("u2", "BOB@example.com"),
        new User("u3", "zoe@example.org")
    );
    users.stream()
        .map(User::getEmail)
        .map(MethodReferenceChallenges_NoHints::domainOf)
        .distinct()
        .sorted(String::compareToIgnoreCase)
        .forEach(System.out::println);
  }

  private static void challenge7() {
    System.out.println("\n[7] Challenge");
    MethodReferenceChallenges_NoHints app = new MethodReferenceChallenges_NoHints();
    Audit audit = new Audit();
    Optional<User> maybe = Optional.of(new User("u1", "ava@example.com"));
    String email = maybe.map(User::getEmail).orElseGet(app::defaultEmail);
    System.out.println("email=" + email);
    maybe.ifPresent(audit::logUser);
  }

  private static void challenge8() {
    System.out.println("\n[8] Challenge");
    List<String> words = List.of("apple", "banana", "apple", "pear", "banana", "banana");
    Map<String, Long> freq = words.stream()
        .collect(Collectors.groupingBy(s -> s, LinkedHashMap::new, Collectors.counting()));
    freq.entrySet().stream()
        .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
        .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
  }

  private static void challenge9() throws IOException {
    System.out.println("\n[9] Challenge");
    List<String> items = new ArrayList<>();
    items.add("x");
    items.add("y");
    System.out.println("items=" + items);
    File tmp = File.createTempFile("mr", ".tmp");
    System.out.println("tmp exists before delete? " + tmp.exists());
    tmp.delete();
    System.out.println("tmp exists after delete?  " + tmp.exists());
  }

  private static void challenge10() throws Exception {
    System.out.println("\n[10] Challenge");
    List<String> list = new ArrayList<>(List.of("a", "b", "c"));
    list.remove("b");
    System.out.println("after removeByValue: " + list);
    System.out.println("valueOf('123')=" + Integer.valueOf("123"));
    Path p = Files.createTempFile("mr-io", ".bin");
    Files.write(p, List.of("hello"), StandardCharsets.UTF_8);
    System.out.println("read bytes=" + Files.readAllBytes(p).length);
  }

  // --- Supporting types & helpers ---

  static final class User {
    private final String id;
    private final String email;
    User(String id, String email) { this.id = id; this.email = email; }
    String getId() { return id; }
    String getEmail() { return email; }
  }

  static final class Service {
    void flush() { System.out.println("Service.flush on thread " + Thread.currentThread().getName()); }
  }

  static final class Audit {
    void logUser(User u) { System.out.println("Audit user: " + u.getId()); }
  }

  static final class Point2 {
    final int x, y;
    Point2(int x, int y) { this.x = x; this.y = y; }
    @Override public String toString() { return "(" + x + "," + y + ")"; }
  }

  static String domainOf(String email) {
    int i = email.indexOf('@');
    return (i >= 0 && i < email.length() - 1) ? email.substring(i + 1) : "";
  }

  String defaultEmail() { return "noreply@example.com"; }
}
