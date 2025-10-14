package java8.lambda.refactor.to.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Validates simple KEY=VALUE lines and parses them into ConfigEntry.
 * TODOs:
 *  - Refactor Predicate to Pattern.asPredicate() (or method reference via helper).
 *  - Refactor Function to a static method reference (ConfigEntry::fromLine).
 *  - Refactor Consumer to System.out::println.
 */
public class Exercise08_Pattern_Before {

  public static void main(String[] args) {
    Pattern envLine = Pattern.compile("^[A-Z_]+=[^\\s]+$");

    List<String> raw = Arrays.asList(
        "API_URL=https://example.com",
        "MAX_THREADS=8",
        "not-a-setting",
        " TIMEOUT=30",
        "ENABLE_CACHE=true"
    );

    // TODO #1: Predicate -> envLine.asPredicate()
    List<ConfigEntry> entries = raw.stream()
        .filter(new Predicate<String>() {
          @Override public boolean test(String s) {
            return envLine.matcher(s).matches();
          }
        })
        // TODO #2: Function -> method reference.
        .map(new Function<String, ConfigEntry>() {
          @Override public ConfigEntry apply(String s) { return ConfigEntry.fromLine(s); }
        })
        .collect(Collectors.toList());

    // TODO #3: Consumer -> method reference.
    entries.forEach(new Consumer<ConfigEntry>() {
      @Override public void accept(ConfigEntry e) { System.out.println(e); }
    });
  }

  // --- Value type ---
  static final class ConfigEntry {
    final String key;
    final String value;

    private ConfigEntry(String key, String value) {
      this.key = key; this.value = value;
    }

    static ConfigEntry fromLine(String line) {
      int i = line.indexOf('=');
      if (i <= 0 || i == line.length() - 1) {
        throw new IllegalArgumentException("Invalid line: " + line);
      }
      return new ConfigEntry(line.substring(0, i), line.substring(i + 1));
    }

    @Override public String toString() { return key + "=" + value; }
  }
}

