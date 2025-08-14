package java8.lambda.methodreferences;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class BoundMethodReferenceExample1 {

  public static void main(String[] args) {

    PrintStream out = System.out;          // must be effectively final if local
    Consumer<String> printer = out::println;
    printer.accept("Hello");

    List<String> fields = Arrays.asList("John", "Jane", "Array", "List");

    Stream<String> required = fields.stream()
        .map(f -> Objects.requireNonNull(f));

    fields.stream().map(Objects::requireNonNull);

    DateTimeFormatter iso = DateTimeFormatter.ISO_LOCAL_DATE;

// Without MR
    Function<LocalDate, String> fmt = d -> iso.format(d);
    Function<LocalDate, String> fmt1 = iso::format;

    


  }
}
