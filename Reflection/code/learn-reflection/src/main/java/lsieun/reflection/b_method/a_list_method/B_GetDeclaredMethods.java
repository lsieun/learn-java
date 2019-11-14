package lsieun.reflection.b_method.a_list_method;

import java.lang.reflect.Method;

public class B_GetDeclaredMethods {
    public static void main(String[] args) {
        final Method[] methods = ChildClass.class.getDeclaredMethods();
        for (final Method m : methods) {
            System.out.println(m.getName());
        }
    }
}
