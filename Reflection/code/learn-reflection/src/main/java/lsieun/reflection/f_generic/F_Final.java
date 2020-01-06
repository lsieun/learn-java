package lsieun.reflection.f_generic;

import java.lang.reflect.*;
import java.util.Arrays;

public class F_Final {
    public static void main(String[] args) {
        Class<ExampleFinal> clazz = ExampleFinal.class;
        Field[] fields = clazz.getDeclaredFields();

        for (Field f : fields) {
            process_field(f);
        }
    }

    public static void process_field(Field f) {
        Type field_type = f.getGenericType();
        System.out.println("Field: " + f.getName());
//        process_type_variable(field_type);
//        process_parameterized_type(field_type);
//        process_wildcard_type(field_type);
//        process_generic_array_type(field_type);
        TypeUtils.processType(field_type);
        System.out.println("================================================");
    }

    public static void process_type_variable(Type t) {
        boolean flag = t instanceof TypeVariable;
        if (!flag) {
            System.out.println("    Type Variable: NO");
            return;
        }
        System.out.println("    Type Variable: YES");

        TypeVariable typeVariable = (TypeVariable) t;

        Type[] bounds = typeVariable.getBounds();

        System.out.println("Type Name: " + typeVariable.getName());
        System.out.println("Type Bounds: " + Arrays.toString(bounds));
        //Types of classes where the output is located
        System.out.println("Type GenericDeclaration: " + typeVariable.getGenericDeclaration());
        System.out.println(t.getClass());

    }

    public static void process_parameterized_type(Type t) {
        boolean flag = t instanceof ParameterizedType;
        if (!flag) {
            System.out.println("    Parameterized Type: NO");
            return;
        }
        System.out.println("    Parameterized Type: YES");

        ParameterizedType parameterizedType = (ParameterizedType) t;
        Type rawType = parameterizedType.getRawType();
        System.out.println("Raw Type: " + rawType);
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        System.out.println("Actual Type Arguments: " + Arrays.toString(actualTypeArguments));
        Type ownerType = parameterizedType.getOwnerType();
        System.out.println("Owner Type: " + ownerType);

        for (Type param_type : actualTypeArguments) {
            process_wildcard_type(param_type);
        }
    }

    public static void process_wildcard_type(Type t) {
        boolean flag = t instanceof WildcardType;
        if (!flag) {
            System.out.println("    Wildcard Type: NO");
            return;
        }
        System.out.println("    Wildcard Type: YES");

        WildcardType wildcardType = (WildcardType) t;
        Type[] upperBounds = wildcardType.getUpperBounds();
        System.out.println("Upper Bounds: " + Arrays.toString(upperBounds));
        Type[] lowerBounds = wildcardType.getLowerBounds();
        System.out.println("Lower Bounds: " + Arrays.toString(lowerBounds));
    }

    public static void process_generic_array_type(Type t) {
        boolean flag = t instanceof GenericArrayType;
        if (!flag) {
            System.out.println("    Generic Array Type: NO");
            return;
        }
        System.out.println("    Generic Array Type: YES");
        GenericArrayType genericArrayType = (GenericArrayType) t;
        Type genericComponentType = genericArrayType.getGenericComponentType();
        System.out.println("Generic Component Type: " + genericComponentType);
    }
}
