package lsieun.reflection.b_method.a_list_method;

import java.lang.reflect.Method;

class A_GetMethods {
    public static void main(String[] args) {
        final Method[] methods = ChildClass.class.getMethods();
        for (final Method m : methods) {
            System.out.println(m.getName());
        }
    }
}
