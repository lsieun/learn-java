package lsieun.reflection.a_clazz;

import lsieun.reflection.a_clazz.bean.Example_A_Modifier;

import java.lang.reflect.Modifier;

public class A_Class_Modifier {
    public static void main(String[] args) {
        Class<?> clazz = Example_A_Modifier.class;
        int modifiers = clazz.getModifiers();
        System.out.println(Modifier.toString(modifiers));
    }
}
