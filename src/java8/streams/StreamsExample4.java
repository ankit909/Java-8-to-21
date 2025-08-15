package java8.streams;

import java.util.stream.Stream;

public class StreamsExample4 {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        System.out.println(stream.count());
        Stream<String> stream1 = Stream.of("a", "b", "c");
        System.out.println(stream1.count());
    }
}
