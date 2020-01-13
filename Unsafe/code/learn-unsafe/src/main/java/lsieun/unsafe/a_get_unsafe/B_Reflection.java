package lsieun.unsafe.a_get_unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class B_Reflection {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        System.out.println("addressSize: " + unsafe.addressSize());
        System.out.println("pageSize: " + unsafe.pageSize());
    }
}
