package lsieun.unsafe.d_object;

import lsieun.unsafe.utils.UnsafeUtils;
import sun.misc.Unsafe;

public class C_ShallowCopy {
    public static void main(String[] args) {
//        Example instance = new Example();
//        System.out.println(instance);
//
//        Unsafe unsafe = UnsafeUtils.getUnsafe();
//
//        long size = UnsafeUtils.sizeOf(instance);
//        System.out.println(size);
//
//        long oldAddress = UnsafeUtils.getAddress(instance);
//        System.out.println(oldAddress);
//        System.out.println(UnsafeUtils.toAddress(instance));

//        long newAddress = unsafe.allocateMemory(size);
//        System.out.println(newAddress);

//        unsafe.copyMemory(oldAddress, newAddress, size);
//        Object obj = UnsafeUtils.fromAddress(newAddress);
//        System.out.println(obj);
//        unsafe.cop
//        Object obj = UnsafeUtils.shallowCopy(instance);
//        System.out.println(obj);

        A a = new A();
        B b = new B();

        long from = UnsafeUtils.toAddress(a);
        long to = UnsafeUtils.toAddress(b);
        long size_a = UnsafeUtils.sizeOf(a);
        long size_b = UnsafeUtils.sizeOf(b);

        System.out.println(from);
        System.out.println(to);
        System.out.println(size_a);
        System.out.println(size_b);

        Unsafe unsafe = UnsafeUtils.getUnsafe();
        unsafe.copyMemory(a, from, b, to, size_a * 8);
        Object obj = UnsafeUtils.fromAddress(to);
        System.out.println(obj);
    }
}
