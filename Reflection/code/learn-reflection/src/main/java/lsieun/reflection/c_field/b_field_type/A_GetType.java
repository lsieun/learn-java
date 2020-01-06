package lsieun.reflection.c_field.b_field_type;

import java.lang.reflect.Type;

class A_GetType {
    public static void main(String[] args) {
        displayFieldName(Example.class,"listValue");
        displayGenericType(Example.class,"listValue");
    }

    public static void displayFieldName(Class<?> clazz, String fieldName) {
        try {
            final Type type = clazz.getDeclaredField(fieldName).getType();
            System.out.println(((Class) type).getName());
            System.out.println(((Class) type).getSimpleName());
            System.out.println(((Class) type).getCanonicalName());
            System.out.println(type.getTypeName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void displayGenericType(Class<?> clazz, String fieldName) {
        try {
            final Type type = clazz.getDeclaredField(fieldName).getGenericType();
            System.out.println(type.getClass());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
