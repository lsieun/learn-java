package lsieun.reflection.f_generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class A_GenericType {
    public static void main(String[] args) {
        try {
            final Type type = Example.class.getDeclaredField("strList").getGenericType();
            if (type instanceof ParameterizedType) {
                final ParameterizedType parameterizedType = (ParameterizedType) type;
                for (final Type typeArgument : parameterizedType.getActualTypeArguments()) {
                    System.out.println(typeArgument);
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
