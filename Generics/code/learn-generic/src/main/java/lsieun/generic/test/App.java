package lsieun.generic.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
//        List<String> mylist = new ArrayList<>();
//        System.out.println(mylist.getClass());
        List<String>[] stringLists = new List[1];
        List<Integer> intList = Arrays.asList(42);
        Object[] objects = stringLists;
        objects[0] = intList;
        String s = stringLists[0].get(0);
        System.out.println(s);
    }
}
