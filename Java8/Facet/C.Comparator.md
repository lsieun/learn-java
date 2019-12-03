# Comparator

```java
public class Person {
    private final String name;
    private final int age;

    public Person(final String theName, final int theAge) {
        name = theName;
        age = theAge;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int ageDifference(final Person other) {
        return age - other.age;
    }

    public String toString() {
        return String.format("%s - %d", name, age);
    }
}
```

```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class C_Comparator {
    private static final List<Person> people = Arrays.asList(
            new Person("John", 20),
            new Person("Sara", 21),
            new Person("Jane", 21),
            new Person("Greg", 35));

    public static void main(String[] args) {
        test_04_sort_then();
    }

    public static void printPeople(final String message, final List<Person> people) {
        System.out.println(message);
        people.forEach(System.out::println);
    }

    public static void test_01_sort_method_reference() {
        List<Person> ascendingAge =
                people.stream()
                        .sorted(Person::ageDifference)
                        .collect(toList());
        printPeople("Sorted in ascending order by age: ", ascendingAge);
    }

    public static void test_02_sort_comparing() {
        List<Person> ascendingName = people.stream()
                .sorted(comparing(Person::getName))
                .collect(toList());
        printPeople("Sorted in ascending order by name: ", ascendingName);
    }

    public static void test_03_sort_reverse() {
        Comparator<Person> compareAscending = Comparator.comparingInt(Person::getAge);
        Comparator<Person> compareDescending = compareAscending.reversed();

        printPeople("Sorted in ascending order by age: ",
                people.stream()
                        .sorted(compareAscending)
                        .collect(toList())
        );

        printPeople("Sorted in descending order by age: ",
                people.stream()
                        .sorted(compareDescending)
                        .collect(toList())
        );
    }

    public static void test_04_sort_then() {
        final Function<Person, Integer> byAge = person -> person.getAge();
        final Function<Person, String> byTheirName = person -> person.getName();
        printPeople("Sorted in ascending order by age and name: ",
                people.stream()
                        .sorted(comparing(byAge).thenComparing(byTheirName))
                        .collect(toList()));
    }

    public static void test_05_sort_min_max() {
        people.stream()
                .min(Person::ageDifference)
                .ifPresent(youngest -> System.out.println("Youngest: " + youngest));

        people.stream()
                .max(Person::ageDifference)
                .ifPresent(eldest -> System.out.println("Eldest: " + eldest));
    }

}
```
