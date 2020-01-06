package lsieun.reflection.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassUtils {
    private static final String EMPTY_STRING = "";

    private static final Method classSignature = ClassUtils.getTargetMethod(Class.class, "getGenericSignature0", new Class<?>[0]);
    private static final Method methodSignature = ClassUtils.getTargetMethod(Method.class, "getGenericSignature", new Class<?>[0]);
    private static final Method fieldSignature = ClassUtils.getTargetMethod(Field.class, "getGenericSignature", new Class<?>[0]);

    public static Method getTargetMethod(Class<?> clazz, String name, Class<?>[] parameterTypes) {
        try {
            Method method = clazz.getDeclaredMethod(name, parameterTypes);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Something Wrong");
    }

    public static String getClassGenericSignature(Class<?> c) {
        return getGenericSignature(classSignature, c);
    }

    public static String getMethodGenericSignature(Method m) {
        return getGenericSignature(methodSignature, m);
    }

    public static String getFieldGenericSignature(Field f) {
        return getGenericSignature(fieldSignature, f);
    }

    public static String getGenericSignature(Method targetMethod, Object obj) {
        try {
            String value = (String) targetMethod.invoke(obj);
            return value;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return EMPTY_STRING;
    }
}
