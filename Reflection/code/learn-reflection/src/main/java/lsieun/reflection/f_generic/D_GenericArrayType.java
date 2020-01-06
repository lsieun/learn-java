package lsieun.reflection.f_generic;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;

public class D_GenericArrayType {
    public static void main(String[] args) {
        Field[] fields = ExampleD.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            //Whether the output current variable is of GenericArrayType type
            System.out.println("Field: "
                    + field.getName()
                    + "; instanceof GenericArrayType"
                    + ": "
                    + (field.getGenericType() instanceof GenericArrayType));

            if (field.getGenericType() instanceof GenericArrayType) {
                //If it is GenericArrayType, the current generic type is output
                System.out.println("Field: "
                        + field.getName()
                        + "; getGenericComponentType()"
                        + ": "
                        + (((GenericArrayType) field.getGenericType()).getGenericComponentType()));
            }
        }
    }
}

