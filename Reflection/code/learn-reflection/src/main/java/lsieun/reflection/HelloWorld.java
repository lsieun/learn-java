package lsieun.reflection;

import java.util.ArrayList;

public final class HelloWorld {

    public Object obj = new ArrayList<>();

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        HelloWorld.test(10);
    }

    public static <T> void test(Object obj) {
        T t = (T) obj;
        System.out.println(t);
    }


}
