package lsieun.reflection.method;

import java.lang.reflect.Method;

import lsieun.reflection.entity.Operations;

/**
 * use <code>getDeclaredMethod()</code> to get any method defined in the class.
 * This includes <b>public</b>, <b>protected</b>, <b>default access</b>, and even <b>private</b> methods but excludes inherited ones.
 */
public class GetDeclaredMethod {
    public static void main(String[] args) {
        getPrivateMethod();
        getProtectedMethod();
    }

    private static void getPrivateMethod() {
        try {
            Method andPrivateMethod = Operations.class.getDeclaredMethod("privateAnd", boolean.class, boolean.class);
            System.out.println(andPrivateMethod);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static void getProtectedMethod() {
        try {
            Method maxProtectedMethod = Operations.class.getDeclaredMethod("protectedMax", int.class, int.class);
            System.out.println(maxProtectedMethod);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
