package lsieun.generic.g_instanceof;

import java.util.Set;

public class A {
    public void test1(Object o) {
        if (o instanceof Set<?>) {
            Set<?> m = (Set<?>) o;
        }
    }

    public void test2(Object o) {
        if (o instanceof Set) {
            Set<?> m = (Set<?>) o;
        }
    }
}
