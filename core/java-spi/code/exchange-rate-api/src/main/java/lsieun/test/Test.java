package lsieun.test;

public class Test {
    public static void main(String[] args) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        System.out.println(cl.getClass().getName());
    }
}
