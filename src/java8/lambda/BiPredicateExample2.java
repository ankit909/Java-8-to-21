package java8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public class BiPredicateExample2 {

  public static void main(String[] args) {
    BiPredicate<List<String>, List<String>> anyMatch = (list1, list2) -> list1.stream().anyMatch(list2::contains);
    List<String> list1 = Arrays.asList("ankit", "bhawana", "charlie");
    List<String> list2 = Arrays.asList("bhawana", "charlie");
    boolean result = anyMatch.test(list1, list2);
    System.out.println(result);
  }

}
