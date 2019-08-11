package lsieun.reflection.b_method.b_get_one_method;

import java.lang.reflect.Method;

/**
 * use <code>getDeclaredMethod()</code> to get any method defined in the class.
 * This includes <b>public</b>, <b>protected</b>, <b>default access</b>, and even <b>private</b> methods but excludes inherited ones.
 */
public class B_GetDeclaredMethod {
    public static void main(String[] args) {
        getPrivateMethod();
        getProtectedMethod();
    }

    private static void getPrivateMethod() {
        try {
            Method andPrivateMethod = Example.class.getDeclaredMethod("privateAnd", boolean.class, boolean.class);
            System.out.println(andPrivateMethod);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static void getProtectedMethod() {
        try {
            Method maxProtectedMethod = Example.class.getDeclaredMethod("protectedMax", int.class, int.class);
            System.out.println(maxProtectedMethod);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
