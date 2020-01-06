package lsieun.reflection.b_method.d_method_argument;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

//在pom.xml中，为maven-compiler-plugin添加-parameters参数
//<compilerArgument>-parameters</compilerArgument>
public class B_GetArgumentName {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Example.class;
            String methodName = "performAction";
            Class<?>[] parameterTypes = new Class<?>[]{String.class, Runnable.class};

            final Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
            Parameter[] parameters = method.getParameters();
            for (Parameter p : parameters) {
                System.out.println(p.getName());
            }

            // 下面是使用Stream，做个对比
            Arrays.stream(parameters)
                    .map(Parameter::getName)
                    .forEach(System.out::println);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
