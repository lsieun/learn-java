package lsieun.generic.create;

import lsieun.generic.bean.Pair;

import java.util.ArrayList;
import java.util.List;

public class Create_Array_2 {
    public void test_1_raw_type() {
    }

    public void test_2_unbounded_wildcard() {
        Object[] pairArr = new Pair<?, ?>[10];         // fine
        pairArr[0] = new Pair<Long, Long>(0L, 0L);     // fine
        pairArr[0] = new Pair<String, String>("", ""); // fine
        pairArr[0] = new ArrayList<String>();          // fails with ArrayStoreException

        Pair<?, ?>[] arr = new Pair<?, ?>[2];          // fine
    }

    public void test_3_bounded_wildcard() {
        Pair<? extends Number, ? extends Number>[] arr = null;
//        arr = new Pair<? extends Number, ? extends Number>[2];  // error: generic array creation
    }

    public void test_4_concrete_parameterized_type() {
        Pair<Double, Double>[] arr = null;
//        arr = new Pair<Double, Double>[2];    // error: generic array creation
    }

    public static void main(String[] args) {
    }
}
