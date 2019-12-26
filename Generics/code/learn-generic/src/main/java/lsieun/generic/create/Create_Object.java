package lsieun.generic.create;

import java.util.ArrayList;

public class Create_Object {
    public void test_1_raw_type() {
        ArrayList list = new ArrayList(); // warning: Raw use of parameterized class 'ArrayList'
    }

    public void test_2_unbounded_wildcard() {
        // Won't compile
//        ArrayList<?> list1 = new ArrayList<?>(); // error

//        ArrayList<?> list2 = new ArrayList<? extends Number>(); // error

        ArrayList<?> list3 = new ArrayList<String>();

        ArrayList<?> list4 = new ArrayList<>();
    }

    public void test_3_bounded_wildcard() {
//        ArrayList<? extends Number> list1 = new ArrayList<? extends Number>(); // error

//        ArrayList<? extends Number> list2 = new ArrayList<String>(); // error: String与Number不兼容
        ArrayList<? extends Number> list3 = new ArrayList<Integer>(); // fine

        ArrayList<? extends Number> list4 = new ArrayList<>();
    }

    public void test_4_concrete_parameterized_type() {
        ArrayList<String> strList = new ArrayList<String>();

        // Java 7: Diamond Syntax
        ArrayList<String> strList2 = new ArrayList<>();
    }
}
