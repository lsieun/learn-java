package lsieun.reflection.f_generic;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Optional;

public class B_ParameterizedType {
    public static void main(String[] args) {
        Class<?> clazz = Example.class;
        String fieldName = "rawList";

        Optional<ParameterizedType> result = getParameterizedType(clazz, fieldName);
        if (!result.isPresent()) {
            String line = String.format("'%s' is Not ParameterizedType", fieldName);
            System.out.println(line);
            return;
        }

        ParameterizedType parameterizedType = result.get();

        test_01_Raw_Type(parameterizedType);
        test_02_Owner_Type(parameterizedType);
        test_03_Owner_Type(parameterizedType);
    }

    public static Optional<ParameterizedType> getParameterizedType(Class<?> clazz, String fieldName) {
        Optional<ParameterizedType> result = Optional.empty();
        try {
            Field field = clazz.getDeclaredField(fieldName);
            Type type = field.getGenericType();
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                result = Optional.of(parameterizedType);
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void test_01_Raw_Type(ParameterizedType parameterizedType) {
        Type rawType = parameterizedType.getRawType();
        System.out.println("Raw Type: " + rawType);
    }

    /**
     * Map.Entry<String, Date> 这种类型的字段，可以满足要求
     * @param parameterizedType
     */
    public static void test_02_Owner_Type(ParameterizedType parameterizedType) {
        Type ownerType = parameterizedType.getOwnerType();
        System.out.println("Owner Type: " + ownerType);
    }

    public static void test_03_Owner_Type(ParameterizedType parameterizedType) {
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        System.out.println("Actual Type Arguments: " + Arrays.toString(actualTypeArguments));
    }
}
