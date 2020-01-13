package lsieun.reflection.a_create;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class A_CreateObject {
    public static void main(String[] args) {
        try {
            Class<String> clazz = String.class;
            final Constructor<String> constructor = clazz.getConstructor(clazz);
            final String str = constructor.newInstance("sample string");
            System.out.println(str);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
