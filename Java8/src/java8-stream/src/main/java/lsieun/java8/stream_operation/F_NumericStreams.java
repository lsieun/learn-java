package lsieun.java8.stream_operation;

import lsieun.java8.domain.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class F_NumericStreams {
    public static void main(String[] args) {
        test_pythagorean_triples();
    }

    public static void test_pythagorean_triples() {
        Stream<double[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0));
        pythagoreanTriples.forEach(F_NumericStreams::printArray);
    }

    public static void printArray(double[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void test_even_numbers() {
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());
    }

    public static void test_IntStream_range() {
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);
    }

    public static void test_IntStream_max() {
        List<Dish> menu = Dish.getMenu();
        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int max = maxCalories.orElse(1);
        System.out.println(max);
    }

    public static void test_boxed() {
        List<Dish> menu = Dish.getMenu();
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();
    }

    public static void test_mapToInt() {
        List<Dish> menu = Dish.getMenu();
        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(calories);
    }

    public static void test_reduce_sum() {
        List<Dish> menu = Dish.getMenu();
        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println(calories);
    }
}
