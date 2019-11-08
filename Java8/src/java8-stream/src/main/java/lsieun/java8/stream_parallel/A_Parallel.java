package lsieun.java8.stream_parallel;

import java.util.stream.Stream;

public class A_Parallel {
    public static void main(String[] args) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("availableProcessors = " + availableProcessors);
    }

    private static void parallel_sum() {
        int n = 100000000;
        Long value = Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
        System.out.println("value = " + value);
    }
}
