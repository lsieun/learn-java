package lsieun.reflection.b_method.d_method_argument;

import java.lang.reflect.Method;
import java.util.Arrays;

public class A_GetArgumentType {
    private static final String FORMAT = "%s: %s";

    public static void main(String[] args) {
        Class<?> clazz = Example.class;
        Method[] methods = clazz.getDeclaredMethods();

        for (Method m : methods) {
            Class<?>[] parameterTypes = m.getParameterTypes();
            String line = String.format(FORMAT, m.getName(), Arrays.toString(parameterTypes));
            System.out.println(line);
        }
    }
}
