package lsieun.generic.g_instanceof;

import java.util.List;

public class Demo {
    public void test_0_best(Object obj) {
        if (obj instanceof List) { // 注意：这里是 raw type
            List<?> list = (List<?>) obj; // 注意：这里是 unbounded wildcard parameterized type
        }
    }

    public void test_1_raw_type(Object obj) {
        if (obj instanceof List) {
            List list = (List) obj; // warning: Raw use of parameterized class 'List'
        }
    }

    public void test_2_unbounded_wildcard(Object obj) {
        if (obj instanceof List<?>) {
            List<?> list = (List<?>) obj;
        }
    }

    public void test_3_bounded_wildcard(Object obj) {
//        if (obj instanceof List<? extends Number>) { // error: Illegal generic type for instanceof
//            List<? extends Number> list = (List<? extends Number>) obj; // Unchecked cast: 'java.lang.Object' to 'java.util.List<? extends java.lang.Number>'
//        }
    }

    public void test_4_concrete(Object obj) {
//        if (obj instanceof List<String>) { // error: Illegal generic type for instanceof
//            List<String> list = (List<String>) obj; // Unchecked cast: 'java.lang.Object' to 'java.util.List<java.lang.String>'
//        }
    }
}
