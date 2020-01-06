package lsieun.reflection.f_generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class A_GenericType {
    public static final String FORMAT = "%s: %s";

    public static void main(String[] args) {
        printFieldInfo(Example.class, "strList");
        printFieldInfo(Example.class, "dateList");
        printFieldInfo(Example.class, "tList");
        printFieldInfo(Example.class, "map");
        printFieldInfo(Example.class, "rawList");
    }

    public static void printFieldInfo(Class<?> clazz, String fieldName) {
        try {
            final Type type = clazz.getDeclaredField(fieldName).getGenericType();
            if (type instanceof ParameterizedType) {
                final ParameterizedType parameterizedType = (ParameterizedType) type;
                for (final Type typeArgument : parameterizedType.getActualTypeArguments()) {
                    System.out.println(String.format(FORMAT, fieldName, typeArgument));
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
