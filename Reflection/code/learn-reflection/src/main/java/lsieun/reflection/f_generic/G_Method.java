package lsieun.reflection.f_generic;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

public class G_Method {
    public static void main(String[] args) throws NoSuchMethodException {
        Class<ExampleG> clazz = ExampleG.class;
        String methodName = "test";
        Method targetMethod = clazz.getDeclaredMethod(methodName, Number.class, List.class);
        System.out.println(targetMethod);

        Type[] genericParameterTypes = targetMethod.getGenericParameterTypes();
        for (Type t : genericParameterTypes) {
            TypeUtils.processType(t);
        }

        System.out.println("================================================");

        Class<?>[] parameterTypes = targetMethod.getParameterTypes();
        for (Class<?> c : parameterTypes) {
            TypeUtils.processType(c);
        }
    }
}
