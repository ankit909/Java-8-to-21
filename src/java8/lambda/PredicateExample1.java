package java8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateExample1 {

  public static void main(String[] args) {
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
    Predicate<String> greaterThan5 = name -> name.length() > 5;
    Predicate<String> startsWithC = name -> name.startsWith("C");
    Predicate<String> both = greaterThan5.and(startsWithC);
    names.stream().filter(both).forEach(System.out::println);
  }
}
