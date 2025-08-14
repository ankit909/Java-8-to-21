package java8.lambda;

import java.util.List;
import java.util.function.Consumer;

//Print or log a value
public class ConsumerExample2 {

  public static void main(String[] args) {
    List<String> users = List.of("Alice", "Bob", "Charlie");
    Consumer<String> printUser = user -> System.out.println("User: " + user);
    users.forEach(printUser);
  }
}
