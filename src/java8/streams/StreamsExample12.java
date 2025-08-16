package java8.streams;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsExample12 {

  public static void main(String[] args) {
    Map<String, Integer> map = Stream.of("one", "two", "three", "four")
        .collect(Collectors.toMap(e -> e, e -> e.length()));
    System.out.println(map);
    Map<Integer, String> map2 = Stream.of("cake", "biscuit", "tart")
        .collect(
            Collectors.toMap(e -> e.length(),
            e -> e,
            (e1, e2) -> e1 + "," + e2));
    System.out.println(map2);
    Map<String, Integer> map3 = Stream.of("cake", "biscuits", "apple tart", "tart")
        .collect(Collectors.toMap(e -> e,
            e ->e.length(),
            (len1, len2) -> len1 + len2,
            () -> new TreeMap<>()));
    System.out.println(map3);
  }

}
