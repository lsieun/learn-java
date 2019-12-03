package lsieun.java8.facet;

import java.util.Arrays;
import java.util.List;

public class A_Collections_01_Iteration {
    private static final List<String> friends =
            Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    public static void main(String[] args) {
        test_04_iteration_method_reference();
    }

    public static void test_01_iteration_for_loop() {
        for (int i = 0; i < friends.size(); i++) {
            System.out.println(friends.get(i));
        }
    }

    public static void test_02_iteration_for_each() {
        for (String name : friends) {
            System.out.println(name);
        }
    }

    public static void test_03_iteration_lambda() {
        friends.forEach(name -> System.out.println(name));
    }

    public static void test_04_iteration_method_reference() {
        friends.forEach(System.out::println);
    }
}
