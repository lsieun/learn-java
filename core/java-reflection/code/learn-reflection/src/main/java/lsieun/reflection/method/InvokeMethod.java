package lsieun.reflection.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lsieun.reflection.entity.Operations;

public class InvokeMethod {
    public static void main(String[] args) {
        invokeInstanceMethod();
        invokeStaticMethod();
        invokePrivateMethod();
        invokeProtectedMethod();
    }

    private static void invokeInstanceMethod() {
        try {
            Method sumInstanceMethod = Operations.class.getMethod("publicSum", int.class, double.class);

            Operations instance = new Operations();
            Double result = (Double) sumInstanceMethod.invoke(instance, 1, 3);
            System.out.println("1 + 3 = " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void invokeStaticMethod() {
        try {
            Method multiplyStaticMethod = Operations.class.getMethod("publicStaticMultiply", float.class, long.class);
            Double result = (Double) multiplyStaticMethod.invoke(null, 3.5f, 2);
            System.out.println("3.5 * 2 = " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>By default, not all reflected methods are accessible</b>.
     * This means that the JVM enforces access control checks when invoking them.
     * <p>
     *     By calling <b>setAccesible(true)</b> on a reflected method object,
     *     the JVM suppresses the access control checks and allows us to invoke the method without throwing an exception.
     * </p>
     */
    private static void invokePrivateMethod() {
        try {
            Method andPrivateMethod = Operations.class.getDeclaredMethod("privateAnd", boolean.class, boolean.class);
            andPrivateMethod.setAccessible(true);

            Operations instance = new Operations();
            Boolean result = (Boolean) andPrivateMethod.invoke(instance, true, false);
            System.out.println("true && false == " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void invokeProtectedMethod() {
        try {
            Method maxProtectedMethod = Operations.class.getDeclaredMethod("protectedMax", int.class, int.class);
            maxProtectedMethod.setAccessible(true);

            Operations instance = new Operations();
            Integer result = (Integer) maxProtectedMethod.invoke(instance, 2, 4);
            System.out.println("2 and 4, the greater one is: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
