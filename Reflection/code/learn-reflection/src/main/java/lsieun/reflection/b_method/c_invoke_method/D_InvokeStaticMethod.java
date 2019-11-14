package lsieun.reflection.b_method.c_invoke_method;

import java.lang.reflect.Method;

public class D_InvokeStaticMethod {

    public static void main(String[] args) {
        invokeStaticMethod();
    }


    private static void invokeStaticMethod() {
        try {
            Method multiplyStaticMethod = Example.class.getMethod("publicStaticMultiply", float.class, long.class);
            Double result = (Double) multiplyStaticMethod.invoke(null, 3.5f, 2);
            System.out.println("3.5 * 2 = " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
