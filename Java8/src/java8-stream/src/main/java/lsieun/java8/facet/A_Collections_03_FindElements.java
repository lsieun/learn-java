package lsieun.java8.facet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class A_Collections_03_FindElements {
    private static final List<String> friends =
            Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    public static void main(String[] args) {
        test_03_find_elements();
    }

    public static void test_01_find_elements() {
        final List<String> startsWithN = new ArrayList<String>();
        for (String name : friends) {
            if (name.startsWith("N")) {
                startsWithN.add(name);
            }
        }
        System.out.println(startsWithN);
    }

    public static void test_02_find_elements() {
        final List<String> startsWithN =
                friends.stream()
                        .filter(name -> name.startsWith("N"))
                        .collect(Collectors.toList());
        System.out.println(startsWithN);
    }

    public static Predicate<String> checkIfStartsWith(final String letter) {
        return name -> name.startsWith(letter);
    }

    public static void test_03_find_elements() {
        final long countFriendsStartN =
                friends.stream()
                        .filter(checkIfStartsWith("N")).count();
        final long countFriendsStartB =
                friends.stream()
                        .filter(checkIfStartsWith("B")).count();

        System.out.println(countFriendsStartN);
        System.out.println(countFriendsStartB);
    }

    public static void test_04_find_elements() {
//        final Function<String, Predicate<String>> startsWithLetter =
//                (String letter) -> {
//                    Predicate<String> checkStarts = (String name) -> name.startsWith(letter);
//                    return checkStarts;
//                };

//        final Function<String, Predicate<String>> startsWithLetter =
//                (String letter) -> (String name) -> name.startsWith(letter);

        final Function<String, Predicate<String>> startsWithLetter =
                letter -> name -> name.startsWith(letter);

        final long countFriendsStartN =
                friends.stream()
                        .filter(startsWithLetter.apply("N")).count();
        final long countFriendsStartB =
                friends.stream()
                        .filter(startsWithLetter.apply("B")).count();

        System.out.println(countFriendsStartN);
        System.out.println(countFriendsStartB);
    }
}
