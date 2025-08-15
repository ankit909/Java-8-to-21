package java8.streams;

import java.util.Optional;
import java.util.stream.Stream;

public class StreamsExample7 {
    public static void main(String[] args) {
        Optional<String> firstName = Stream.of("John", "deer", "horse")
                .max((s1, s2) -> s1.compareTo(s2));
        firstName.ifPresent(System.out::println);
    }
}
