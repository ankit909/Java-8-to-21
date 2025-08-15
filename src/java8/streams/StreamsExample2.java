package java8.streams;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamsExample2 {
    public static void main(String[] args) {
        Double[] numbers = {1.1, 2.2, 3.3, 4.4, 5.5};
        Stream<Double> stream = Arrays.stream(numbers);
        long n = stream.count();
        System.out.println(n);
    }
}
