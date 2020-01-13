package lsieun.unsafe.d_object;

import lsieun.unsafe.utils.UnsafeUtils;
import sun.misc.Unsafe;

public class D_CopyArray {


    public static void main(String[] args) {
        int N = 10;
        Unsafe unsafe = UnsafeUtils.getUnsafe();

        byte b[] = new byte[N];
        long addressOfObject = getAddressOfObject(unsafe, b);
//        unsafe.copyMemory(b, 16, null, directOffset, N);
    }

    public static long getAddressOfObject(Unsafe unsafe, Object obj) {
        Object array[] = new Object[1];
        array[0] = obj;
        long baseOffset = unsafe.arrayBaseOffset(Object[].class);
        long addressOfObject = unsafe.getLong(array, baseOffset);
        return addressOfObject;
    }
}
