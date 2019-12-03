package lsieun.java8.facet;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class A_Collections_05_Reduce {
    private static final List<String> friends =
            Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    public static void main(String[] args) {
        test_03_reduce();
    }

    public static void test_01_reduce() {
        System.out.println("Total number of characters in all names: " +
                friends.stream()
                        .mapToInt(name -> name.length())
                        .sum());
    }

    public static void test_02_reduce() {
        final Optional<String> aLongName =
                friends.stream()
                        .reduce((name1, name2) ->
                                name1.length() >= name2.length() ? name1 : name2);
        aLongName.ifPresent(name ->
                System.out.println(String.format("A longest name: %s", name)));
    }

    public static void test_03_reduce() {
        final String steveOrLonger =
                friends.stream()
                        .reduce("Steve", (name1, name2) ->
                                name1.length() >= name2.length() ? name1 : name2);
        System.out.println(steveOrLonger);
    }
}
