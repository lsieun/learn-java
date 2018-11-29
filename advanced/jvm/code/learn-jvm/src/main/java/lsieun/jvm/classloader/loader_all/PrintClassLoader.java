package lsieun.jvm.classloader.loader_all;

public class PrintClassLoader {
    public static void main(String[] args) {
        // String class is loaded by bootstrap loader, and
        // bootstrap loader is not Java object, hence null
        System.out.println(String.class.getClassLoader());
        System.out.println("===========================");

        // Test class is loaded by Application loader
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(PrintClassLoader.class.getClassLoader());
        System.out.println("===========================");
    }
}
