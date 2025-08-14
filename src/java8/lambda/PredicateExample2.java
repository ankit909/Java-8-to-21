package java8.lambda;

//customer gets a discount if product price exceeds a threshold and
// they are a “Gold” customer.

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Product {
  private String type;
  private Integer price;

  Product(String type, Integer price) {
    this.type = type;
    this.price = price;
  }

  public String getType() {
    return type;
  }

  public Integer getPrice() {
    return price;
  }
}



public class PredicateExample2 {

  private List<Product> getFilteredProducts(List<Product> products) {
    Predicate<Product> predicate1 = p1 -> p1.getType().equals("Gold");
    Predicate<Product> predicate2 = p2 -> p2.getPrice() > 1100;
    Predicate<Product> predicate = predicate1.and(predicate2);
    List<Product> filteredProducts = products.stream().filter(predicate).collect(Collectors.toList());
    return filteredProducts;
  }

  public static void main(String[] args) {
    Product product1 = new Product("Gold", 1200);
    Product product2 = new Product("Silver", 1000);
    List<Product> products = Arrays.asList(product1, product2);
    PredicateExample2 predicateExample4 = new PredicateExample2();
    predicateExample4.getFilteredProducts(products).stream().forEach(product -> System.out.println(product.getType()));
  }
}
