package java8.lambda.refactor.to.lambda;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;

public class Exercise3FileFilter_After {

  public static void main(String[] args) throws Exception {
    // Prepare a temp directory with some files
    Path dir = Files.createTempDirectory("demo-logs");
    Path log1 = dir.resolve("app.log");
    Path log2 = dir.resolve("debug.log");
    Path txt  = dir.resolve("notes.txt");
    Path png  = dir.resolve("image.png");

    Files.write(log1, "log A".getBytes(StandardCharsets.UTF_8));
    Files.write(log2, "log B".getBytes(StandardCharsets.UTF_8));
    Files.write(txt,  "not a log".getBytes(StandardCharsets.UTF_8));
    Files.write(png,  new byte[]{(byte)137, 80, 78, 71});

    // Stagger timestamps so sorting by lastModified is visible
    long now = System.currentTimeMillis();
    new File(log1.toString()).setLastModified(now - 10_000); // older
    new File(log2.toString()).setLastModified(now - 5_000);  // newer

    File folder = dir.toFile();

    // TODO #4: Refactor this anonymous FilenameFilter to a lambda.
    File[] logs = folder.listFiles((d, name) -> name.toLowerCase().endsWith(".log"));
    if (logs == null) logs = new File[0];

    // TODO #5: Refactor this anonymous Comparator to a method reference–based comparator.
    Arrays.sort(logs, Comparator.comparingLong(File::lastModified)
    );

    System.out.println("=== .log files sorted by lastModified (oldest → newest) ===");
    for (File f : logs) {
      System.out.println(f.getName() + " (" + f.lastModified() + ")");
    }

    System.out.println("\nTemp directory: " + dir.toAbsolutePath());
    // Uncomment to clean up after verifying:
    // deleteRecursively(folder);
  }

  // Optional cleanup utility
  static void deleteRecursively(File f) {
    if (f.isDirectory()) {
      File[] children = f.listFiles();
      if (children != null) {
        for (File c : children) deleteRecursively(c);
      }
    }
    f.delete();
  }
}
