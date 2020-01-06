package lsieun.generic;

import lsieun.generic.bean.Pair;

import java.util.ArrayList;
import java.util.List;

public class HelloWorld {
    public static <E> void addAll(List<E> list, E... array) {  // varargs warning
        for (E element : array) list.add(element);
    }

    static <T> T[] method_1(T t1, T t2) {
        return method_2(t1, t2);                       // unchecked warning
    }

    @SafeVarargs
    static <T> T[] method_2(T... args) {
        return args;
    }

    public void test_Object(Object obj) {
        Class<?> clazz = obj.getClass();
    }

    public void test_String(String str) {
        Class<? extends String> clazz = str.getClass();
    }

    public void test_Number(Number num) {
        Class<? extends Number> clazz = num.getClass();
        Class<Number> num_clazz = num.getClass();
    }

    public void test(List<? extends String> bounded_wildcard_list, List<String> str_list) {
        bounded_wildcard_list  = str_list;
        //str_list = bounded_wildcard_list;
    }

    public static void main(String... args) {
        String[] strings = method_1("bad", "karma");     // ClassCastException

        List<? extends String> str_list = new ArrayList<>();
        List<? extends String> str_list2 = str_list;
        List<String> str3_list = str_list;

        Object obj = null;
        Class<?> obj_clazz = obj.getClass();

        String str = null;
        Class<? extends String> str_clazz = str.getClass();

        Number num = null;
        Class<? extends Number> num_clazz = num.getClass();
    }
}
