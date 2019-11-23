package lsieun.java8.stream_operation;

import lsieun.java8.domain.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class B_Filter_And_Slice {
    public static void main(String[] args) {
        test_skip();
    }

    public static void test_skip() {
        List<Dish> menu = Dish.getMenu();
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
        System.out.println(dishes);
    }

    public static void test_limit() {
        List<Dish> menu = Dish.getMenu();
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(dishes);
    }

    public static void test_distinct() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    public static void test_filter() {
        List<Dish> menu = Dish.getMenu();
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        System.out.println(vegetarianMenu);
    }
}
