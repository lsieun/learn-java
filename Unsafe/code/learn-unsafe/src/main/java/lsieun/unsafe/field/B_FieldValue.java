package lsieun.unsafe.field;

import lsieun.unsafe.utils.UnsafeUtils;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class B_FieldValue {
    public static void main(String[] args) throws NoSuchFieldException {
        Example instance = new Example();
        Field f = Example.class.getField("intValue");

        Unsafe unsafe = UnsafeUtils.getUnsafe();
        long offset = unsafe.objectFieldOffset(f);
        System.out.println(offset);

        int value = unsafe.getInt(instance, offset);
        System.out.println(value);
    }
}
