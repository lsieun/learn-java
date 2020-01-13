package lsieun.unsafe.d_object;

import lsieun.unsafe.utils.UnsafeUtils;
import sun.misc.Unsafe;

public class F_Copy {
    public static void main(String[] args) {
        Example instance = new Example();

        long oldAddress = UnsafeUtils.getAddress(instance);
        long size = UnsafeUtils.sizeOf(instance);

        System.out.println(oldAddress);
        System.out.println(size);

        Unsafe unsafe = UnsafeUtils.getUnsafe();
        long offset = unsafe.allocateMemory(size);
        offset = offset / 8;
        System.out.println(offset);

        unsafe.copyMemory(instance, 0, null, offset, size);

        Object obj = UnsafeUtils.fromAddress(offset);
        System.out.println(obj);
    }
}
