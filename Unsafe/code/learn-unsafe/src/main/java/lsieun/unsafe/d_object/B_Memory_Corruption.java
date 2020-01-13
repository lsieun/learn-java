package lsieun.unsafe.d_object;

import lsieun.unsafe.utils.UnsafeUtils;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class B_Memory_Corruption {
    public static void main(String[] args) throws NoSuchFieldException {
        Guard guard = new Guard();
        boolean value1 = guard.giveAccess();// false, no access
        System.out.println(value1);

        // bypass
        Unsafe unsafe = UnsafeUtils.getUnsafe();
        Field f = Guard.class.getDeclaredField("ACCESS_ALLOWED");
        unsafe.putInt(guard, unsafe.objectFieldOffset(f), 42); // memory corruption

        boolean value2 = guard.giveAccess();// true, access granted
        System.out.println(value2);
    }
}
