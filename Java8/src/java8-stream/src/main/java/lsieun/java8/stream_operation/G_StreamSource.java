package lsieun.java8.stream_operation;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class G_StreamSource {
    public static void main(String[] args) {
        test_generate();
    }

    public static void test_stream_from_values() {
        Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
    }

    public static void test_stream_from_arrays() {
        int[] numbers = {1, 2, 3, 4};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);
    }

    public static void test_stream_from_files() {
        String filepath = System.getProperty("user.dir") + "/target/classes/data.txt";

        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get(filepath), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("file://" + filepath);
        System.out.println(uniqueWords);
    }

    public static void test_iterate() {
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }

    public static void test_generate() {
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}
