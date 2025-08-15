package java8.streams;

import java.util.stream.Stream;

public class StreamsExample6 {
    public static void main(String[] args) {
        Stream.iterate(2, n -> n + 2).limit(10).forEach(System.out::println);
    }

}