package java8.lambda.refactor.to.lambda;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Creates a temp dir, writes a few files, lists only *.log, then sorts by file name.
 * TODOs:
 *  - Refactor DirectoryStream.Filter to a lambda.
 *  - Refactor Comparator<Path> to a method-reference-friendly comparator.
 */
public class Exercise05_Files_After {

  public static void main(String[] args) throws IOException {
    Path dir = Files.createTempDirectory("demo-logs-");
    Files.write(dir.resolve("app.log"),   "log A".getBytes(StandardCharsets.UTF_8));
    Files.write(dir.resolve("debug.log"), "log B".getBytes(StandardCharsets.UTF_8));
    Files.write(dir.resolve("notes.txt"), "not a log".getBytes(StandardCharsets.UTF_8));

    List<Path> logs = new ArrayList<>();

    try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir,
        entry -> entry.getFileName().toString().toLowerCase().endsWith(".log"))) {
      for (Path p : stream) {
        logs.add(p);
      }
    }

    logs.sort(Comparator.comparing(path -> path.getFileName().toString(), String.CASE_INSENSITIVE_ORDER));

    System.out.println("=== .log files (sorted by name) ===");
    logs.forEach(p -> System.out.println(p.getFileName()));

    System.out.println("\nTemp dir: " + dir.toAbsolutePath());
    // Optional: Files.walk(dir).sorted(Comparator.reverseOrder()).forEach(p -> p.toFile().delete());
  }
}
