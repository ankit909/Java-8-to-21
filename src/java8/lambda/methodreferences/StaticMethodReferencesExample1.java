package java8.lambda.methodreferences;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StaticMethodReferencesExample1 {

  public static void main(String[] args) {
    List<String> lines = Arrays.asList("John", "Jane");
    // Without MR
    List<Integer> ids = lines.stream()
        .map(s -> Integer.parseInt(s))
        .toList();

    List<Integer> ids1 = lines.stream().map(Integer::parseInt).toList();

    Function<String, Integer> f1L = s -> Integer.parseInt(s); // lambda
    Function<String, Integer> f2L =  Integer::parseInt;

    // Without MR
    List<String> fields = Arrays.asList("John", "Jane", "Array", "List");
    Stream<String> required = fields.stream()
        .map(f -> Objects.requireNonNull(f));
    Stream<String> optional = fields.stream().map(Objects::requireNonNull);
    Stream.of("1","2","3").map(Integer::parseInt).forEach(System.out::println);

    BiFunction<Integer, Integer, Integer> maxer = (a, b) -> Math.max(a, b);
    BiFunction<Integer, Integer, Integer> maxer1 = Math::max;


  }

}
