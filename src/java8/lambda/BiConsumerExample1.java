package java8.lambda;

import java.util.Map;
import java.util.function.BiConsumer;

//printing both keys and value of a map
public class BiConsumerExample1 {

  public static void main(String[] args) {
    Map<String, Integer> stock = Map.of("APPLE", 1, "ORANGE", 2, "PAPER", 3);
    BiConsumer<String, Integer> biConsumer = (k, v) -> System.out.println(k + ": " + v);
    stock.forEach(biConsumer);
  }
}
