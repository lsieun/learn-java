package lsieun.reflection.b_method.d_method_argument_and_return;

import java.lang.reflect.Method;

public class C_GetReturnType {
    public static void main(String[] args) throws NoSuchMethodException {
        printMethodReturnType(Example.class, "sum", new Class<?>[]{int.class, int.class});
        printMethodReturnType(Example.class, "doSomthing", new Class<?>[]{String.class});
    }

    public static void printMethodReturnType(Class<?> clazz, String methodName, Class<?>[] paramTypes) throws NoSuchMethodException {
        Method targetMethod = clazz.getDeclaredMethod(methodName, paramTypes);
        Class<?> returnType = targetMethod.getReturnType();
        System.out.println(returnType);
    }
}
