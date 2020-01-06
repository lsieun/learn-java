package lsieun.reflection.f_generic;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

public class C_TypeVariable {
    public static void main(String[] args) {
        Type[] types = ExampleC.class.getTypeParameters();
        for (Type type : types) {
            TypeVariable t = (TypeVariable) type;
            Type[] bounds = t.getBounds();

            System.out.println("Name: " + t.getName());
            System.out.println("Bounds: " + Arrays.toString(bounds));
            //Types of classes where the output is located
            System.out.println("GenericDeclaration: " + t.getGenericDeclaration());
            System.out.println("================================================");
        }
    }
}
