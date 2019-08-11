package lsieun.reflection.c_field.a_list_field;

import java.lang.reflect.Field;

public class A_01_ListFields {
    public static void main(String[] args) {
        final Field[] fields = ChildClass.class.getFields();
        for (final Field f : fields) {
            System.out.println(f.getName());
        }
    }
}
