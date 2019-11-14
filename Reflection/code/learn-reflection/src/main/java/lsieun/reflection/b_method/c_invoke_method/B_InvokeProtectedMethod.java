package lsieun.reflection.b_method.c_invoke_method;

import java.lang.reflect.Method;

public class B_InvokeProtectedMethod {

    public static void main(String[] args) {
        invokeProtectedMethod();
    }

    private static void invokeProtectedMethod() {
        try {
            Method maxProtectedMethod = Example.class.getDeclaredMethod("protectedMax", int.class, int.class);
            maxProtectedMethod.setAccessible(true);

            Example instance = new Example();
            Integer result = (Integer) maxProtectedMethod.invoke(instance, 2, 4);
            System.out.println("2 and 4, the greater one is: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
