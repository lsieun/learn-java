package lsieun.unsafe;

import lsieun.unsafe.utils.UnsafeUtils;
import sun.misc.Unsafe;

public class HelloWorld {
    public static void main(String[] args) {
        Unsafe unsafe = UnsafeUtils.getUnsafe();
        long baseOffset = unsafe.arrayBaseOffset(Object[].class);
        int addressSize = unsafe.addressSize();
        System.out.println(baseOffset);
        System.out.println(addressSize);

        String abc = "Hello";

        Object[] array = new Object[] {abc};

        long objectAddress;
        switch (addressSize)
        {
            case 4:
                objectAddress = unsafe.getInt(array, baseOffset);
                break;
            case 8:
                objectAddress = unsafe.getLong(array, baseOffset);
                break;
            default:
                throw new Error("unsupported address size: " + addressSize);
        }
        System.out.println(objectAddress);
    }
}
