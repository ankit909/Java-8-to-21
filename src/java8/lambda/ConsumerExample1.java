package java8.lambda;

import java.util.function.Consumer;

public class ConsumerExample1 {

  public static void main(String[] args) {
    Consumer consumer = s -> System.out.println(s);
    consumer.accept("Hello");
  }

}
