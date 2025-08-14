package java8.streams;

import java.util.stream.Stream;

public class StreamsExample1  {

  public static void main(String[] args) {
    Stream.of("Alex", "Bob", "Charlie", "David")
        .map(s -> {System.out.println("map "+s);
        return s.toUpperCase();
        })
        .anyMatch( s-> { System.out.println("anyMatch "+ s);
          return s.startsWith("A");
        });
  }

}
