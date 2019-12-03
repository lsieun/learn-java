package lsieun.java8.facet;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class A_Collections_06_JoinElements {
    private static final List<String> friends =
            Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    public static void main(String[] args) {
        test_05_join();
    }

    public static void test_01_join() {
        for(String name : friends) {
            System.out.print(name + ", ");
        }
        System.out.println();
    }

    public static void test_02_join() {
        for(int i = 0; i < friends.size() - 1; i++) {
            System.out.print(friends.get(i) + ", ");
        }
        if(friends.size() > 0)
            System.out.println(friends.get(friends.size() - 1));
    }

    public static void test_03_join() {
        System.out.println(String.join(", ", friends));
    }

    public static void test_04_join() {
        System.out.println(
                friends.stream()
                        .map(String::toUpperCase)
                        .collect(joining(", ")));
    }

    public static void test_05_join() {
        System.out.println(
                friends.stream()
                        .collect(joining(", ", "[", "]")));
    }
}
