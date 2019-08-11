package lsieun.reflection.b_method.b_get_one_method;

import java.lang.reflect.Method;

/**
 * <code>getMethod()</code> to find any public method,
 * be it <b>static</b> or <b>instance</b>
 * that is defined in the class or any of its superclasses.
 * <p>
 *     It receives <b>the method name</b> as the first argument,
 *     followed by <b>the types</b> of the method’s arguments.
 * </p>
 */
public class A_GetPublicMethod {
    public static void main(String[] args) {
        getInstanceMethod();
        getStaticMethod();
    }

    private static void getInstanceMethod()  {
        try {
            Method sumInstanceMethod = Example.class.getMethod("publicSum", int.class, double.class);
            System.out.println(sumInstanceMethod);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    private static void getStaticMethod() {
        try {
            Method multiplyStaticMethod = Example.class.getMethod("publicStaticMultiply", float.class, long.class);
            System.out.println(multiplyStaticMethod);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


}
