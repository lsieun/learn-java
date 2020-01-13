package lsieun.unsafe.field;

import lsieun.unsafe.utils.UnsafeUtils;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class A_FieldOffset {
    public static void main(String[] args) throws NoSuchFieldException {
        print_field_offset(Example.class, "intValue");
    }

    public static void print_field_offset(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        Field field = clazz.getDeclaredField(fieldName);

        Unsafe unsafe = UnsafeUtils.getUnsafe();
        long offset = unsafe.objectFieldOffset(field);

        String line = String.format("%s: %s", field.getName(), offset);
        System.out.println(line);
    }
}
