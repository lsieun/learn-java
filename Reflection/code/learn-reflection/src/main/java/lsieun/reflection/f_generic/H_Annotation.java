package lsieun.reflection.f_generic;

import java.lang.reflect.TypeVariable;
import java.util.Arrays;

public class H_Annotation {
    public static void main(String[] args) {
        Class<?> clazz = Example_Annotation.class;
        System.out.println(clazz);

        TypeVariable<?>[] typeParameters = clazz.getTypeParameters();
        Arrays.stream(typeParameters).forEach(TypeUtils::processType);
    }
}
