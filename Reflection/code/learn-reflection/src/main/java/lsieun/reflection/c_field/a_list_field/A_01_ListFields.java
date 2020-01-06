package lsieun.reflection.c_field.a_list_field;

import java.lang.reflect.Field;

public class A_01_ListFields {
    public static void main(String[] args) {
        test_1_get_public_fields(ChildClass.class);
        System.out.println(System.lineSeparator());
        System.out.println("====================");
        test_2_get_declared_fields(ChildClass.class);
    }

    public static void test_1_get_public_fields(Class<?> clazz) {
        final Field[] fields = clazz.getFields();
        for (final Field f : fields) {
            System.out.println(f.getName());
        }
    }

    public static void test_2_get_declared_fields(Class<?> clazz) {
        final Field[] fields = clazz.getDeclaredFields();
        for (final Field f : fields) {
            System.out.println(f.getName());
        }
    }
}
