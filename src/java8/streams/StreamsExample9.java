package java8.streams;

import java.util.stream.Stream;

public class StreamsExample9 {
    public static void main(String[] args) {
        String name = Stream.of("John", "Smith", "Jane")
                .reduce("", (a, b) -> a + b);
        System.out.println(name);
    }
}
