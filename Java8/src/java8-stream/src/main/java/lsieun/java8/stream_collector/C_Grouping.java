package lsieun.java8.stream_collector;

import lsieun.java8.domain.CaloricLevel;
import lsieun.java8.domain.Dish;

import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class C_Grouping {
    public static void main(String[] args) {
        test_groupingBy_set();
    }

    public static void test_groupingBy() {
        List<Dish> menu = Dish.getMenu();
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(dishesByType);
    }

    public static void test_groupingBy_CaloricLevel() {
        List<Dish> menu = Dish.getMenu();
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }));
        System.out.println(dishesByCaloricLevel);
    }

    public static void test_multilevel_groupingBy() {
        List<Dish> menu = Dish.getMenu();
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        }))
        );
        System.out.println(dishesByTypeCaloricLevel);
    }

    public static void test_groupingBy_count() {
        List<Dish> menu = Dish.getMenu();
        Map<Dish.Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println(typesCount);
    }

    public static void test_groupingBy_sum() {
        List<Dish> menu = Dish.getMenu();
        Map<Dish.Type, Integer> totalCaloriesByType = menu.stream()
                .collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println(totalCaloriesByType);
    }

    public static void test_groupingBy_set() {
        List<Dish> menu = Dish.getMenu();
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
                menu.stream().collect(
                        groupingBy(Dish::getType, mapping(
                                dish -> {
                                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                },
                                toSet())));
        System.out.println(caloricLevelsByType);
    }

    public static void test_groupingBy_set2() {
        List<Dish> menu = Dish.getMenu();
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
                menu.stream().collect(
                        groupingBy(Dish::getType, mapping(
                                dish -> {
                                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                },
                                toCollection(HashSet::new))));
        System.out.println(caloricLevelsByType);
    }

    public static void test_groupingBy_max() {
        List<Dish> menu = Dish.getMenu();
        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                menu.stream().collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType);
    }

    public static void test_groupingBy_max_then() {
        List<Dish> menu = Dish.getMenu();
        Map<Dish.Type, Dish> mostCaloricByType = menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
        System.out.println(mostCaloricByType);
    }
}
