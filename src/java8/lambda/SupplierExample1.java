package java8.lambda;

import java.util.function.Supplier;

public class SupplierExample1 {

  public static void main(String[] args) {
    Supplier<Double> supplier = () -> Math.random();
    System.out.println(supplier.get());
  }
}
