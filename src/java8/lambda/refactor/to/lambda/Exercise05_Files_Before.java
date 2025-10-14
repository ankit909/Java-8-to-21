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
public class Exercise05_Files_Before {

  public static void main(String[] args) throws IOException {
    Path dir = Files.createTempDirectory("demo-logs-");
    Files.write(dir.resolve("app.log"),   "log A".getBytes(StandardCharsets.UTF_8));
    Files.write(dir.resolve("debug.log"), "log B".getBytes(StandardCharsets.UTF_8));
    Files.write(dir.resolve("notes.txt"), "not a log".getBytes(StandardCharsets.UTF_8));

    List<Path> logs = new ArrayList<>();

    // TODO #1: DirectoryStream.Filter -> lambda.
    try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, new DirectoryStream.Filter<Path>() {
      @Override public boolean accept(Path entry) {
        return entry.getFileName().toString().toLowerCase().endsWith(".log");
      }
    })) {
      for (Path p : stream) {
        logs.add(p);
      }
    }

    // TODO #2: Comparator<Path> -> method-reference-friendly comparator (case-insensitive by name).
    logs.sort(new Comparator<Path>() {
      @Override public int compare(Path a, Path b) {
        String an = a.getFileName().toString();
        String bn = b.getFileName().toString();
        return an.compareToIgnoreCase(bn);
      }
    });

    System.out.println("=== .log files (sorted by name) ===");
    for (Path p : logs) {
      System.out.println(p.getFileName());
    }

    System.out.println("\nTemp dir: " + dir.toAbsolutePath());
    // Optional: Files.walk(dir).sorted(Comparator.reverseOrder()).forEach(p -> p.toFile().delete());
  }
}

