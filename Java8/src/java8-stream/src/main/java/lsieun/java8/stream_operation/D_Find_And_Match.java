package lsieun.java8.stream_operation;

import lsieun.java8.domain.Dish;

import java.util.List;

public class D_Find_And_Match {
    public static void main(String[] args) {
        test_anyMatch();
    }

    public static void test_anyMatch() {
        List<Dish> menu = Dish.getMenu();
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!");
        }
    }
}
