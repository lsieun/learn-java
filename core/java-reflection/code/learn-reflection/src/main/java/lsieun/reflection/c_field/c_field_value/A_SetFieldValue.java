package lsieun.reflection.c_field.c_field_value;

import java.lang.reflect.Field;

public class A_SetFieldValue {
    public static void main(String[] args) {

        try {
            final Example instance = new Example();
            final Field field = Example.class.getDeclaredField("name");
            // A value of true indicates that the reflected object should suppress
            // Java language access checking when it is used. A value of false indicates
            // that the reflected object should enforce Java language access checks.
            field.setAccessible(true); // 对于private字段
            field.set(instance, "sample name");
            System.out.println(instance.getName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
