package java8.lambda;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class BiPredicateExample3  {
  // Validate if a string is non-null and matches a
  // minimum length (common in user input validation).
  public static void main(String[] args) {
    BiPredicate<String, Integer> biPredicate = (a,b) -> !a.isBlank() && a.length() > b;
    System.out.println(biPredicate.test("Hey Handsome", 7));
  }
}
