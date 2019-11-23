package lsieun.java8.stream_operation;

import java.util.Arrays;
import java.util.List;

public class E_Reduce {
    public static void main(String[] args) {
        test_reduce_sum();
    }

    public static void test_reduce_sum() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        //int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);
    }

    public static void test_reduce_mul() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println(product);
    }
}
