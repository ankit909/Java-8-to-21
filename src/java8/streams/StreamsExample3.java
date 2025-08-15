package java8.streams;

import java.util.HashMap;
import java.util.Map;

public class StreamsExample3 {
    public static void main(String[] args) {
        Map<String, Integer> namesToAge = new HashMap<>();
        namesToAge.put("John", 30);
        namesToAge.put("Jane", 40);
        namesToAge.put("Jack", 20);
        namesToAge.put("Jill", 25);
        System.out.println("number of entries: " + namesToAge.size());
        System.out.println("number of entries: " + namesToAge.entrySet().stream().count());
    }
}
