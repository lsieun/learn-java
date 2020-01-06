package lsieun.reflection.f_generic;

import lsieun.reflection.utils.ClassUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parse_FieldDefinition {
    public static void main(String[] args) {
        parse_field(ExampleFinal.class, "t_Array");
        parse_field(ExampleFinal.class, "cmp");
    }

    public static void parse_field(Class<?> clazz, String fieldName) {
        Field[] fields = clazz.getDeclaredFields();
        List<Field> list = Arrays.stream(fields).filter(m -> m.getName().equals(fieldName)).collect(Collectors.toList());
        for (Field item : list) {
            System.out.println(item);
            System.out.println(item.toGenericString());
            System.out.println("Generic Signature: " + ClassUtils.getFieldGenericSignature(item));
            TypeUtils.processType(item.getGenericType());
            System.out.println("===============================================================");
        }
    }
}
