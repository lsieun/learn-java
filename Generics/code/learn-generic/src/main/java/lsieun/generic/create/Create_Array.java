package lsieun.generic.create;

import lsieun.generic.bean.Pair;

import java.util.List;

public class Create_Array {
    public void test_0_best() {
        // 我个人觉得，最好的办法就是不使用“泛型数组”
        // 注意：是个人看法，很难说是“正确的用法”
    }

    public void test_1_raw_type() {
        List[] array = new List[10];
    }

    public void test_2_unbounded_wildcard() {
        List<?>[] array = new List<?>[10];
    }

    public void test_3_bounded_wildcard() {
//        List<? extends Number>[] array = new List<? extends Number>[10]; // error
    }

    public void test_4_concrete_parameterized_type() {
//        List<String>[] array = new List<String>[10]; // error
    }
}
