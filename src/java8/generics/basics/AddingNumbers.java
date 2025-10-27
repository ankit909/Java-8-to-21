package java8.generics.basics;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


// It uses ? extends Number and ? super T.
public class AddingNumbers {

    static double sum(List<? extends Number> numbers) {
        return numbers.stream().mapToDouble(Number::doubleValue).sum();
    }

    static <T> void addAll(Collection<? super T> dest, Iterable<T> src) {
           src.forEach(dest::add);
    }

    public static void main(String[] args) {
        List<? extends Number> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1.0, 2.0, 3.0);
        System.out.println(sum(numbers));
        List<Integer> ints = Arrays.asList(1,2,3,4,5);
        List<Double> doubles = Arrays.asList(1.0,2.0,3.0);
        List<Number> numberList = new ArrayList<>();
        List<Double> doubleList = new ArrayList<>();
        addAll(numberList, ints);
        addAll(doubleList, doubles);
        System.out.println(numberList);
        System.out.println(doubleList);
    }
}
