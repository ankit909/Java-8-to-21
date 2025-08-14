package java8.lambda;

import java.util.function.Consumer;

@FunctionalInterface
interface Printable<T> {
  void print(T t);
}

public class LambdaExercise1 {

  public static void main(String[] args) {
    consumer();
  }

  static void consumer() {
    Printable<String> printable = (s) -> System.out.println(s);
    printable.print("Printable lambda");

    Consumer<String> consumer = (s) -> System.out.println(s);
    consumer.accept("Printable lambda");

  }
}
