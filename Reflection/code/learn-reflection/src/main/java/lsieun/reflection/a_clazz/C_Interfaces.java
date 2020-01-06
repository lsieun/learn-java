package lsieun.reflection.a_clazz;

import lsieun.reflection.a_clazz.bean.Example_C_Interfaces;

import java.util.Arrays;

public class C_Interfaces {
    public static void main(String[] args) {
        Class<?> clazz = Example_C_Interfaces.class;
        Class<?>[] interfaces = clazz.getInterfaces();
        System.out.println(Arrays.toString(interfaces));
    }
}
