package lsieun.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class HelloWorld {
    public static void main(String[] args) {
        sort(null, 1, 10);
    }

    private static void sort(long a[], int offset, int length) {
        assert a != null;
        assert offset >= 0 && offset <= a.length;
        assert length >= 0 && length <= a.length - offset;
    }
}
