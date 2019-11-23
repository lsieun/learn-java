package lsieun.java8.stream_operation;

import lsieun.java8.domain.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class A_Collections_VS_Streams {
    public static void main(String[] args) {
        internal_iteration();
    }

    // Streams: internal iteration
    public static void internal_iteration() {
        List<Dish> menu = Dish.getMenu();
        List<String> names =
                menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(names);
    }

    // Collections: external iteration with a for-each loop
    public static void external_iteration() {
        List<Dish> menu = Dish.getMenu();
        List<String> names = new ArrayList<>();
        for (Dish d : menu) {
            names.add(d.getName());
        }
        System.out.println(names);
    }

    public static void traverse_only_once() {
        List<String> list = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = list.stream();
        s.forEach(System.out::println);
        s.forEach(System.out::println);
    }

    public static void test1() {
        List<Dish> menu = Dish.getMenu();
        List<String> threeHighCaloricDishNames =
                menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .map(Dish::getName)
                        .limit(3)
                        .collect(Collectors.toList());
        System.out.println(threeHighCaloricDishNames);
    }
}
