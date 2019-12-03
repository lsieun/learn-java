package lsieun.java8.stream_parallel.bean;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> {
    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        System.out.println("tryAdvance: " + string + "|-->" + currentChar + "(" + string.charAt(currentChar) + ")");
        action.accept(string.charAt(currentChar++));
        return currentChar < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
//        System.out.println("trySplit: " + string);
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            return null;
        }
        for (int splitPos = currentSize / 2 + currentChar;
             splitPos < string.length(); splitPos++) {
            if (Character.isWhitespace(string.charAt(splitPos))) {
                Spliterator<Character> spliterator =
                        new WordCounterSpliterator(string.substring(currentChar, splitPos));
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
//        System.out.println("estimateSize: " + string);
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
//        System.out.println("characteristics: " + string);
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
