package lsieun.java8.stream_operation;

import lsieun.java8.domain.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class C_Map {
    public static void main(String[] args) {
        test_flatMap2();
    }

    public static void test_flatMap2() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                .filter(j -> (i + j) % 3 == 0)
                                .map(j -> new int[]{i, j})
                        )
                        .collect(Collectors.toList());
        pairs.forEach(a -> System.out.println(Arrays.toString(a)));
    }

    public static void test_flatMap() {
        List<String> words = Arrays.asList("Hello", "World");
        List<String> chars = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(chars);
    }

    public static void test_map() {
        List<Dish> menu = Dish.getMenu();
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(dishNames);

        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(dishNameLengths);
    }
}
