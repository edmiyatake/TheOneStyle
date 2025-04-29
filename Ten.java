import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Ten {

    public static void main(String[] args) throws Exception {
        new TheOne<>(args[0])
                .bind(Ten::readFile)
                .bind(Ten::filterChars)
                .bind(Ten::normalize)
                .bind(Ten::scan)
                .bind(Ten::removeStopWords)
                .bind(Ten::frequencies)
                .bind(Ten::sort)
                .bind(Ten::top25Freqs)
                .printMe();
    }

    // The monad-like wrapper
    public static class TheOne<T> {
        private T value;

        public TheOne(T value) {
            this.value = value;
        }

        public <R> TheOne<R> bind(Function<T, R> func) {
            return new TheOne<>(func.apply(value));
        }

        public void printMe() {
            System.out.println(value);
        }
    }

    // Step functions
    public static String readFile(String path) {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String filterChars(String text) {
        return text.replaceAll("[\\W_]+", " ");
    }

    public static String normalize(String text) {
        return text.toLowerCase();
    }

    public static List<String> scan(String text) {
        return Arrays.asList(text.split("\\s+"));
    }

    public static List<String> removeStopWords(List<String> words) {
        try {
            List<String> stopWords = new ArrayList<>(Arrays.asList(
                    Files.readString(Paths.get("stopwords.txt")).split(",")
            ));
            for (char ch = 'a'; ch <= 'z'; ch++) {
                stopWords.add(String.valueOf(ch));
            }
            return words.stream()
                    .filter(w -> !stopWords.contains(w))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Integer> frequencies(List<String> words) {
        Map<String, Integer> freqs = new HashMap<>();
        for (String word : words) {
            freqs.put(word, freqs.getOrDefault(word, 0) + 1);
        }
        return freqs;
    }

    public static List<Map.Entry<String, Integer>> sort(Map<String, Integer> freqs) {
        return freqs.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    public static String top25Freqs(List<Map.Entry<String, Integer>> sortedFreqs) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (Map.Entry<String, Integer> entry : sortedFreqs) {
            if (count++ == 25) break;
            sb.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
