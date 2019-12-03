package lsieun.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class HelloWorld {
    public static void main(String[] args) {
        final List<String> friends =
                Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
        final List<String> editors =
                Arrays.asList("Brian", "Jackie", "John", "Mike");
        final List<String> comrades =
                Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");

        final long countFriendsStartN =
                friends.stream()
                        .filter(checkIfStartsWith("N")).count();
        final long countFriendsStartB =
                friends.stream()
                        .filter(checkIfStartsWith("B")).count();
    }

    public static Predicate<String> checkIfStartsWith(final String letter) {
        return name -> name.startsWith(letter);
    }
}
