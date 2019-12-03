package lsieun.java8.stream_parallel;

import lsieun.java8.stream_parallel.bean.WordCounter;
import lsieun.java8.stream_parallel.bean.WordCounterSpliterator;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class E_Implement_Spliterator {
    public static final String SENTENCE =
            "Nel    mezzo del cammin    di nostra vita " +
            "mi ritrovai in una selva oscura" +
            " ché la dritta via era    smarrita ";

    public static void main(String[] args) {
        test4();
    }

    public static void test1() {
        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");
    }

    public static void test2() {
        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
        System.out.println("Found " + countWords(stream) + " words");
    }

    public static void test3() {
        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
        System.out.println("Found " + countWords(stream.parallel()) + " words");
    }

    public static void test4() {
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + countWords(stream) + " words");
    }

    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }

    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }
}
