package lsieun.java8.facet;

import lsieun.java8.facet.bean.Person;

import java.util.*;
import java.util.function.BinaryOperator;

import static java.util.stream.Collectors.*;

public class Group {
    private static final List<Person> people = Arrays.asList(
            new Person("John", 20),
            new Person("Sara", 21),
            new Person("Jane", 21),
            new Person("Greg", 35));

    public static void main(String[] args) {
        test_03_groupBy();
    }

    public static void test_01_groupBy_name() {
        Map<Integer, List<Person>> peopleByAge =
                people.stream()
                        .collect(groupingBy(Person::getAge));
        System.out.println("Grouped by age: " + peopleByAge);
    }

    public static void test_02_groupBy() {
        Map<Integer, List<String>> nameOfPeopleByAge =
                people.stream()
                        .collect(
                                groupingBy(
                                        Person::getAge,
                                        mapping(Person::getName, toList())));
        System.out.println("People grouped by age: " + nameOfPeopleByAge);
    }

    public static void test_03_groupBy() {
        Comparator<Person> byAge = Comparator.comparing(Person::getAge);
        Map<Character, Optional<Person>> oldestPersonOfEachLetter =
                people.stream()
                        .collect(groupingBy(person -> person.getName().charAt(0),
                                reducing(BinaryOperator.maxBy(byAge))));
        System.out.println("Oldest person of each letter:");
        System.out.println(oldestPersonOfEachLetter);
    }
}
