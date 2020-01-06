package lsieun.reflection.a_clazz;

import lsieun.reflection.a_clazz.bean.Example_B_SuperClass;

public class B_SuperClass {
    public static void main(String[] args) {
        Class<?> clazz = Example_B_SuperClass.class;
        Class<?> superclass = clazz.getSuperclass();
        System.out.println(superclass);
    }
}
