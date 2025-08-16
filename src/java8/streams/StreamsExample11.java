package java8.streams;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsExample11 {

  public static void main(String[] args) {
    String s = Stream.of("Hello", "World", "!").collect(Collectors.joining(", "));
    System.out.println(s);
    Double d = Stream.of("Cake", "biscuits", "apple tart")
              .collect(Collectors.averagingInt(s1 -> s1.length()));
    System.out.println(d);
  }
}
