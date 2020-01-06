package lsieun.reflection.f_generic;

import lsieun.reflection.utils.ClassUtils;

import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Map;

public class Parse_ClassDefinition {
    public static void main(String[] args) {
        parse_class(Comparable.class);
        parse_class(Enum.class);
        parse_class(Map.class);
        parse_class(Example_TypeParameter_Bound.class);
    }

    public static void parse_class(Class<?> clazz) {
        System.out.println(clazz);
        System.out.println(clazz.toGenericString());
        System.out.println("Generic Signature: " + ClassUtils.getClassGenericSignature(clazz));

        TypeVariable<?>[] typeParameters = clazz.getTypeParameters();
        Arrays.stream(typeParameters).forEach(TypeUtils::processType);
        System.out.println("================================================================");
    }
}
