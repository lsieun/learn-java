package lsieun.java8.facet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class A_Collections_02_Transform {
    private static final List<String> friends =
            Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    public static void main(String[] args) {
        test_03_transform();
    }

    public static void test_01_transform() {
        final List<String> uppercaseNames = new ArrayList<>();
        for (String name : friends) {
            uppercaseNames.add(name.toUpperCase());
        }
        System.out.println(uppercaseNames);
    }

    public static void test_02_transform() {
        List<String> uppercaseNames = friends.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(uppercaseNames);
    }

    public static void test_03_transform() {
        friends.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
