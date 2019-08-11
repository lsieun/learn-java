package lsieun.reflection.b_method.c_invoke_method;

import java.lang.reflect.Method;

public class C_InvokePrivateMethod {

    public static void main(String[] args) {
        invokePrivateMethod();
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
            Method andPrivateMethod = Example.class.getDeclaredMethod("privateAnd", boolean.class, boolean.class);
            andPrivateMethod.setAccessible(true);

            Example instance = new Example();
            Boolean result = (Boolean) andPrivateMethod.invoke(instance, true, false);
            System.out.println("true && false == " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
