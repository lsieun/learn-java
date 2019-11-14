package lsieun.reflection.g_invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class B {
    public static void main(String[] args) {
        Object rcvr = "a";
        System.out.println(rcvr.getClass());
        try {
            MethodType mt = MethodType.methodType(int.class);
            MethodHandles.Lookup l = MethodHandles.lookup();
            MethodHandle mh = l.findVirtual(rcvr.getClass(), "hashCode", mt);
            int ret;
            try {
                ret = (int) mh.invoke(rcvr);
                System.out.println(ret);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        } catch (IllegalArgumentException |
                NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        }
    }
}
