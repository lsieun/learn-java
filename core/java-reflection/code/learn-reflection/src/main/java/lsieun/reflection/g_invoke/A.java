package lsieun.reflection.g_invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

public class A {
    public static void main(String[] args) {
        try {
            final Lookup lookup = MethodHandles.lookup();
            final MethodType methodType = MethodType.methodType(int.class);
            final MethodHandle methodHandle = lookup.findVirtual(String.class, "length", methodType);
            final int length = (int) methodHandle.invokeExact("sample string");
            System.out.println("The length of the string is " + length + " characters");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
