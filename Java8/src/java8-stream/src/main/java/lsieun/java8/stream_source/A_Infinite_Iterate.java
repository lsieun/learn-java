package lsieun.java8.stream_source;

import java.util.stream.Stream;

public class A_Infinite_Iterate {
    public static void main(String[] args) {
        print_1_to_n(3);
        sum_1_to_n(10);
    }

    private static void print_1_to_n(int n) {
        Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .forEach(System.out::println);
    }

    private static void sum_1_to_n(int n) {
        Long value = Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
        System.out.println("value = " + value);
    }
}
