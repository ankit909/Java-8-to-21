package java8.streams;

import java.util.stream.Stream;

public class StreamsExample10 {

  public static void main(String[] args) {
    StringBuilder word = Stream.of("ad", "jud", "i", "cate")
        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
    System.out.println(word);
  }

}
