package lsieun.reflection.a_create;

import java.lang.reflect.Array;
import java.util.Arrays;

public class B_CreateArray {

    public static void main(String[] args) {
        String[] str_array = create_array(String.class, 10);
        System.out.println(Arrays.toString(str_array));

        Integer[] int_array = create_array(Integer.class, 10);
        System.out.println(Arrays.toString(int_array));

//        int[] array = create_array(int.class, 10); // error
    }

    public static <T> T[] create_array(Class<?> clazz, int length) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) Array.newInstance(clazz, length);
        return array;
    }
}
