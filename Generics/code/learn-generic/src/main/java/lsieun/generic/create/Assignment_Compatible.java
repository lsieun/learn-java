package lsieun.generic.create;

import java.util.ArrayList;
import java.util.List;

public class Assignment_Compatible {
    private List raw_list = new ArrayList<>(); // warning: Raw use of parameterized class 'List'
    private List<?> unbounded_wildcard_list = new ArrayList<>();
    private List<? extends Number> bounded_wildcard_list = new ArrayList<>();
    private List<Integer> concrete_parameterized_list = new ArrayList<>();

    public void test_1_raw_type() {
        raw_list = unbounded_wildcard_list; // fine
        raw_list = bounded_wildcard_list; // fine
        raw_list = concrete_parameterized_list; // fine
    }

    public void test_2_unbounded_wildcard() {
        unbounded_wildcard_list = raw_list; // fine
        unbounded_wildcard_list = bounded_wildcard_list; // fine
        unbounded_wildcard_list = concrete_parameterized_list; // fine
    }

    public void test_3_bounded_wildcard() {
        bounded_wildcard_list = raw_list; // warning: Unchecked assignment
//        bounded_wildcard_list = unbounded_wildcard_list; // error
//        bounded_wildcard_list = concrete_parameterized_list; // fine
    }

    public void test_4_concrete_parameterized_type() {
        concrete_parameterized_list = raw_list;  // warning: Unchecked assignment
//        concrete_parameterized_list = unbounded_wildcard_list; // error
//        concrete_parameterized_list = bounded_wildcard_list; // error
    }

}
