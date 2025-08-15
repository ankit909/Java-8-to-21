package java8.streams;

import java.util.stream.Stream;

// infinite stream of random unordered numbers between 0..9 inclusive
public class StreamsExample5 {
    public static void main(String[] args) {
        Stream<Integer> infStream = Stream.generate(() -> (int) (Math.random() * 10));
        infStream.forEach(System.out::println);
    }
}
