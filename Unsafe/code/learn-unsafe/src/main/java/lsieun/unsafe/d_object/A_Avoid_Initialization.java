package lsieun.unsafe.d_object;

import lsieun.unsafe.utils.UnsafeUtils;
import sun.misc.Unsafe;

public class A_Avoid_Initialization {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        // (1) new
        Example instance1 = new Example();
        System.out.println(instance1.getI());

        // (2) Reflection
        Example instance2 = Example.class.newInstance();
        System.out.println(instance2.getI());

        // (3) Unsafe
        Unsafe unsafe = UnsafeUtils.getUnsafe();
        Example instance3 = (Example) unsafe.allocateInstance(Example.class);
        System.out.println(instance3.getI());
    }
}
