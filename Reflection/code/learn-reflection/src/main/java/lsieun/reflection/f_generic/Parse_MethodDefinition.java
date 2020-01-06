package lsieun.reflection.f_generic;

import lsieun.reflection.utils.ClassUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Parse_MethodDefinition {

    public static void main(String[] args) {
        parse_method(Comparable.class, "compareTo");
        parse_method(List.class, "sort");
        parse_method(Map.class, "put");
    }

    public static void parse_method(Class<?> clazz, String methodName) {
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> list = Arrays.stream(methods).filter(m -> m.getName().equals(methodName)).collect(Collectors.toList());
        for (Method item : list) {
            System.out.println(item);
            System.out.println(item.toGenericString());
            System.out.println("Generic Signature: " + ClassUtils.getMethodGenericSignature(item));
            System.out.println("Parameter Types: " + Arrays.toString(item.getGenericParameterTypes()));
            System.out.println("Return Type: " + item.getGenericReturnType());
            Type[] genericParameterTypes = item.getGenericParameterTypes();
            Arrays.stream(genericParameterTypes).forEach(TypeUtils::processType);
            TypeUtils.processType(item.getGenericReturnType());
            System.out.println("==============================================");
        }
    }
}
