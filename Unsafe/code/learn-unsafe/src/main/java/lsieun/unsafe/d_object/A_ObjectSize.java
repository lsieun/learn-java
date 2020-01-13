package lsieun.unsafe.d_object;

import lsieun.unsafe.utils.UnsafeUtils;

public class A_ObjectSize {
    public static void main(String[] args) {
        Example instance = new Example();
        long size = UnsafeUtils.sizeOf(instance);
        System.out.println(size);
    }
}
