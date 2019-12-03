package lsieun.java8.facet;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class A_Collections_04_PickAnElement {
    private static final List<String> friends =
            Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    public static void main(String[] args) {
        test_02_pick();
    }

    public static void test_01_pick() {
        final String startingLetter = "N";
        String foundName = null;
        for(String name : friends) {
            if(name.startsWith(startingLetter)) {
                foundName = name;
                break;
            }
        }
        System.out.print(String.format("A name starting with %s: ", startingLetter));
        if(foundName != null) {
            System.out.println(foundName);
        } else {
            System.out.println("No name found");
        }
    }

    public static void test_02_pick() {
        final String startingLetter = "M";
        final Optional<String> foundName =
                friends.stream()
                        .filter(name ->name.startsWith(startingLetter))
                        .findFirst();
        System.out.println(String.format("A name starting with %s: %s",
                startingLetter, foundName.orElse("No name found")));
    }
}
