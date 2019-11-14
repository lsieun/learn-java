package lsieun.reflection.a_clazz;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class B_Constructor {
    public static void main(String[] args){
        try {
            final Constructor<String> constructor = String.class.getConstructor(String.class);
            final String str = constructor.newInstance("sample string");
            final Method method = String.class.getMethod("length");
            final Object length = method.invoke(str);
            System.out.println("The length of the string is " + length + " characters");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
