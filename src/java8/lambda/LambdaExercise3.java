package java8.lambda;


import java.util.function.Predicate;

@FunctionalInterface
interface Evaluate<T> {
  boolean check(T t);
}

public class LambdaExercise3 {

  static void predicate() {
    Evaluate<Integer> eval = (arg) -> arg < 0 ? true: false;
    eval.check(10);
  }

  public static void main(String[] args) {
    predicate();
    Predicate<Integer> predicate = arg -> arg < 0 ? true: false;
    predicate.test(10);
  }
}
