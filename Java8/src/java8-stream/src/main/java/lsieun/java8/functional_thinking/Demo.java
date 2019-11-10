package lsieun.java8.functional_thinking;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        Lookup l = MethodHandles.lookup();
        lookupDefineClass(l);
        String[] words = {"Hello World!", "Hello Generic"};
        Object[] objects = words;
        System.out.println(Arrays.toString(objects));

    }

    public static void lookupDefineClass(Lookup l) {
        // Class<?> defineClass(String name, byte[] b, int off, int len)
        MethodType mt = MethodType.methodType(Class.class, String.class,
                byte[].class, int.class,
                int.class);
        try {
            MethodHandle mh = l.findVirtual(ClassLoader.class, "defineClass", mt);
            System.out.println(mh);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
