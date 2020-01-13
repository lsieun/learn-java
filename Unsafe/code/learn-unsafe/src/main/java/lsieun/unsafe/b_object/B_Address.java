package lsieun.unsafe.b_object;

import lsieun.unsafe.utils.UnsafeUtils;
import sun.misc.Unsafe;

public class B_Address {
    public static void main(String[] args) {
        Example instance = new Example();
        long address = UnsafeUtils.getAddress(instance);
        System.out.println(address);

        Object[] array = new Object[]{instance};
        Unsafe unsafe = UnsafeUtils.getUnsafe();
        long array_address = UnsafeUtils.getAddress(array);
        long newaddress = unsafe.getLong(array_address+16);
        System.out.println(newaddress);
    }
}
