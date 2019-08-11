package lsieun.reflection.c_field.b_field_type;

import java.lang.reflect.Type;

class A_GetType {
    public static void main(String[] args) {
        displayFieldName("boolValue");
    }

    public static void displayFieldName(String fieldName) {
        try {
            final Type type = Example.class.getDeclaredField(fieldName).getType();
            System.out.println(((Class) type).getName());
            System.out.println(((Class) type).getSimpleName());
            System.out.println(((Class) type).getCanonicalName());
            System.out.println(type.getTypeName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
