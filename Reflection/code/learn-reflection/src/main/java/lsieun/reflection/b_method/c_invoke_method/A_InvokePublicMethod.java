package lsieun.reflection.b_method.c_invoke_method;

import java.lang.reflect.Method;

public class A_InvokePublicMethod {

    public static void main(String[] args) {
        invokePublicMethod();
    }

    private static void invokePublicMethod() {
        try {
            Method sumInstanceMethod = Example.class.getMethod("publicSum", int.class, double.class);

            Example instance = new Example();
            Double result = (Double) sumInstanceMethod.invoke(instance, 1, 3);
            System.out.println("1 + 3 = " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
