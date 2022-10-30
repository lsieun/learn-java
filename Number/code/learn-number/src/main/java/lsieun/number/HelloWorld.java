package lsieun.number;

import lsieun.utils.IntegerUtils;

public class HelloWorld {
    private static final int DIM_SIZE = 6;
    private static final int KIND_SIZE = 4;
    private static final int FLAGS_SIZE = 2;
    private static final int VALUE_SIZE = 32 - DIM_SIZE - KIND_SIZE - FLAGS_SIZE;

    private static final int DIM_SHIFT = KIND_SIZE + FLAGS_SIZE + VALUE_SIZE;
    private static final int KIND_SHIFT = FLAGS_SIZE + VALUE_SIZE;
    private static final int FLAGS_SHIFT = VALUE_SIZE;

    // Bitmasks to get each field of an abstract type.

    private static final int DIM_MASK = ((1 << DIM_SIZE) - 1) << DIM_SHIFT;
    private static final int KIND_MASK = ((1 << KIND_SIZE) - 1) << KIND_SHIFT;
    private static final int VALUE_MASK = (1 << VALUE_SIZE) - 1;

    public static void main(String[] args) {
        String str = IntegerUtils.toBinary(VALUE_MASK);
        System.out.println(str);
    }
}
