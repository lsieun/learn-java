package lsieun.reflection.b_method.d_method_argument_name;

import java.lang.reflect.Method;
import java.util.Arrays;

//在pom.xml中，为maven-compiler-plugin添加-parameters参数
//<compilerArgument>-parameters</compilerArgument>
public class A_GetArgumentName {
    public static void main(String[] args) {
        try {
            final Method method = Example.class.getDeclaredMethod("performAction", String.class, Runnable.class);
            Arrays.stream(method.getParameters()).forEach(p -> System.out.println(p.getName()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
