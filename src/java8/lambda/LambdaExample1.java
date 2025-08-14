package java8.lambda;

public class LambdaExample1 {
  public static void main(String[] args) {
    Runnable r1 = new Runnable() {
      public void run() {
        System.out.println("Without Lambda");
      }
    };
    r1.run();

    Runnable r2 = () -> System.out.println("With Lambda");
    r2.run();
  }
}
