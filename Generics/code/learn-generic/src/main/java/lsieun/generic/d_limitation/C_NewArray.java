package lsieun.generic.d_limitation;

import java.util.List;

public class C_NewArray {

    public <T> void performAction(final T action) {
        //T[] actions = new T[0];
    }

    public <T> void test_array_vargs(T... array) {
        //
    }

    public <T> void test_array(T[] array) {
        //
    }

    interface Function<T> {
        T apply(T arg1, T arg2);
    }

    // Naive generic version of reduction - won't compile!
    static <E> E reduce(List<E> list, Function<E> f, E initVal) {
        E[] snapshot = (E[]) list.toArray(); // Locks list
        E result = initVal;
        for (E e : snapshot)
            result = f.apply(result, e);
        return result;
    }

}
