package java8.lambda;

public class LambdaExercise2 {

  @FunctionalInterface
  interface Retrievable<T> {
    T get();
  }

  public static void main(String[] args) {
    Retrievable<Integer> getInt = () -> 77;
    getInt.get();
    Retrievable<Integer> getInt1 = () -> 77;
    getInt1.get();
  }
}
