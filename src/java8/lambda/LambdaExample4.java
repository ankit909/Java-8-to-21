package java8.lambda;

@FunctionalInterface
interface Functionable<I, O> {
  O function(I input);
}



public class LambdaExample4 {

  static void function(Integer f) {
    Functionable<Integer, String> toStringMsg = n -> "Number is: " + n;
    toStringMsg.function(f);
  }

  public static void main(String[] args) {

    function(25);
  }

}
