package lsieun.except.checked;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Example {
    @SuppressWarnings("unused")
    private int test_sample(String str) {
        if (str.length() == 0)
            throw new IllegalArgumentException("The string should have at least one character!");
        System.out.println("Inside test_sample: argument's value equals to: '" + str + "'");
        return 0;
    }

    public static void main(String... args) {
        try {
            Class<?> c1 = Class.forName("lsieun.except.checked.Example");
            Object obj = c1.newInstance();
            Method[] declared_Methods = c1.getDeclaredMethods();
            for (Method method : declared_Methods) {
                String methodName = method.getName();
                if (methodName.contains("main"))
                    continue;

                System.out.format("Invoking %s()%n", methodName);

                try {
                    method.setAccessible(true);
                    Object returnValue = method.invoke(obj, "");
                    System.out.format("%s() returned: %d%n", methodName, returnValue);
                } catch (InvocationTargetException ex) {
                    System.err.println("An InvocationTargetException was caught!");
                    Throwable cause = ex.getCause();
                    System.out.format("Invocation of %s failed because of: %s%n", methodName, cause.getMessage());
                }
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.err.println("The following exception was thrown:");
            ex.printStackTrace();
        }
    }
}
