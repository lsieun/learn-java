package lsieun.reflection.f_generic;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TypeUtils {
    private static final String FORMAT = "    %s: %s";
    private static final String NO = "NO";
    private static final String YES = "YES";

    public static TypeEnum getCategory(Type t) {
        boolean isClass = t instanceof Class;
        boolean isTypeVariable = t instanceof TypeVariable;
        boolean isWildcardType = t instanceof WildcardType;
        boolean isParameterizedType = t instanceof ParameterizedType;
        boolean isGenericArrayType = t instanceof GenericArrayType;

        checkType(isClass, isTypeVariable, isWildcardType, isParameterizedType, isGenericArrayType);

        if (isClass) return TypeEnum.CLASS;
        if (isTypeVariable) return TypeEnum.TYPE_VARIABLE;
        if (isWildcardType) return TypeEnum.WILDCARD_TYPE;
        if (isParameterizedType) return TypeEnum.PARAMETERIZED_TYPE;
        if (isGenericArrayType) return TypeEnum.GENERIC_ARRAY_TYPE;

        throw new RuntimeException("Unknown Type: " + t);
    }

    private static void checkType(
            boolean isClass,
            boolean isTypeVariable,
            boolean isWildcardType,
            boolean isParameterizedType,
            boolean isGenericArrayType) {
        int count = 0;
        if (isClass) count++;
        if (isTypeVariable) count++;
        if (isWildcardType) count++;
        if (isParameterizedType) count++;
        if (isGenericArrayType) count++;
        if (count != 1) {
            throw new RuntimeException("count != 1; count = " + count);
        }
    }

    public static void printType(Type t) {
        System.out.println();
        System.out.println("Type: " + t);
        boolean isClass = t instanceof Class;
        boolean isTypeVariable = t instanceof TypeVariable;
        boolean isWildcardType = t instanceof WildcardType;
        boolean isParameterizedType = t instanceof ParameterizedType;
        boolean isGenericArrayType = t instanceof GenericArrayType;

        if (isClass) {
            System.out.println(String.format(FORMAT, "Class", YES));
        }
        if (isTypeVariable) {
            System.out.println(String.format(FORMAT, "Type Variable", YES));
        }
        if (isWildcardType) {
            System.out.println(String.format(FORMAT, "Wildcard Type", YES));
        }
        if (isParameterizedType) {
            System.out.println(String.format(FORMAT, "Parameterized Type", YES));
        }
        if (isGenericArrayType) {
            System.out.println(String.format(FORMAT, "Generic Array Type", YES));
        }

        checkType(isClass, isTypeVariable, isWildcardType, isParameterizedType, isGenericArrayType);
    }

    public static void processType(Type t) {
        printType(t);
        TypeEnum category = getCategory(t);
        if (category == TypeEnum.CLASS) {
            Class<?> clazz = (Class<?>) t;
            print_class(clazz);
            return;
        }
        if (category == TypeEnum.TYPE_VARIABLE) {
            TypeVariable<?> typeVariable = (TypeVariable<?>) t;
            print_type_variable(typeVariable);
            return;
        }
        if (category == TypeEnum.WILDCARD_TYPE) {
            WildcardType wildcardType = (WildcardType) t;
            print_wildcard_type(wildcardType);
            return;
        }
        if (category == TypeEnum.PARAMETERIZED_TYPE) {
            ParameterizedType parameterizedType = (ParameterizedType) t;
            print_parameterized_type(parameterizedType);
            return;
        }
        if (category == TypeEnum.GENERIC_ARRAY_TYPE) {
            GenericArrayType genericArrayType = (GenericArrayType) t;
            print_generic_array_type(genericArrayType);
            return;
        }
    }

    public static void print_class(Class<?> t) {
        boolean isPrimitive = t.isPrimitive();
        if (isPrimitive) {
            System.out.println(String.format(FORMAT, "Primitive", YES));
        }

        boolean isInterface = t.isInterface();
        if (isInterface) {
            System.out.println(String.format(FORMAT, "Interface", YES));
        }

        boolean isEnum = t.isEnum();
        if (isEnum) {
            System.out.println(String.format(FORMAT, "Enum", YES));
        }

        boolean isArray = t.isArray();
        if (isArray) {
            System.out.println(String.format(FORMAT, "Array", YES));
        }

    }

    public static void print_type_variable(TypeVariable<?> t) {
        Type[] bounds = t.getBounds();
        Annotation[] annotations = t.getAnnotations();

        System.out.println(String.format(FORMAT, "Type Bounds", Arrays.toString(bounds)));
        System.out.println(String.format(FORMAT, "Type Generic Declaration", t.getGenericDeclaration()));
        if (annotations != null && annotations.length > 0) {
            System.out.println(String.format(FORMAT, "Annotations", Arrays.toString(annotations)));
        }
    }

    public static void print_wildcard_type(WildcardType t) {
        Type[] upperBounds = t.getUpperBounds();
        Type[] lowerBounds = t.getLowerBounds();

        System.out.println(String.format(FORMAT, "Upper Bounds", Arrays.toString(upperBounds)));
        System.out.println(String.format(FORMAT, "Lower Bounds", Arrays.toString(lowerBounds)));
    }

    public static void print_parameterized_type(ParameterizedType t) {
        Type rawType = t.getRawType();
        Type[] actualTypeArguments = t.getActualTypeArguments();
        Type ownerType = t.getOwnerType();

        System.out.println(String.format(FORMAT, "Raw Type", rawType));
        System.out.println(String.format(FORMAT, "Actual Type Arguments", Arrays.toString(actualTypeArguments)));
        System.out.println(String.format(FORMAT, "Owner Type", ownerType));

        for (Type param_type : actualTypeArguments) {
            processType(param_type);
        }
    }

    public static void print_generic_array_type(GenericArrayType t) {
        Type genericComponentType = t.getGenericComponentType();
        System.out.println(String.format(FORMAT, "Generic Component Type", genericComponentType));
        processType(genericComponentType);
    }




}
