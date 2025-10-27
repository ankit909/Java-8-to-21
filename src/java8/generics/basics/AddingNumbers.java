package java8.generics.basics;


import java.util.Arrays;
import java.util.List;

public class AddingNumbers {

    static double sum(List<? extends Number> numbers) {
        return numbers.stream().mapToDouble(Number::doubleValue).sum();
    }

    public static void main(String[] args) {
        List<? extends Number> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1.0, 2.0, 3.0);
        System.out.println(sum(numbers));
    }
}
