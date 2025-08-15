package java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamsExample8 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John Doe", "Jane Doe", "Something in the way");
        Predicate<String> predicate = name -> name.startsWith("S");
        System.out.println(names.stream().filter(predicate).count());
        System.out.println(names.stream().findFirst().orElse(null));
        System.out.println(names.stream().anyMatch(predicate));
        System.out.println(names.stream().filter(predicate).collect(Collectors.joining()));
    }
}
