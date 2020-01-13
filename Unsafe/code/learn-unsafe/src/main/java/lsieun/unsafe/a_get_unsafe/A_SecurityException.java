package lsieun.unsafe.a_get_unsafe;

import sun.misc.Unsafe;

/**
 * Runtime: java.lang.SecurityException: Unsafe
 *
 * java -Xbootclasspath:/usr/jdk1.7.0/jre/lib/rt.jar:. com.mishadoff.magic.UnsafeClient
 */
public class A_SecurityException {
    public static void main(String[] args) {
        Unsafe unsafe = Unsafe.getUnsafe();
    }
}
