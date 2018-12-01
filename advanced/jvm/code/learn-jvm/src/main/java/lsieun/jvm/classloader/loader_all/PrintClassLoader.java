package lsieun.jvm.classloader.loader_all;

import com.sun.crypto.provider.SunJCE;

public class PrintClassLoader {
    public static void main(String[] args) {
        // String class is loaded by bootstrap loader, and
        // bootstrap loader is not Java object, hence null
        System.out.println("Bootstrap Class Loader: " + String.class.getClassLoader());
        System.out.println("===========================");

        System.out.println("Extension Class Loader: " + SunJCE.class.getClassLoader());
        System.out.println("Parent: " + SunJCE.class.getClassLoader().getParent());
        System.out.println("===========================");

        // Test class is loaded by Application loader
        System.out.println("System Class Loader: " + ClassLoader.getSystemClassLoader());
        System.out.println("System Class Loader: " + Thread.currentThread().getContextClassLoader());
        System.out.println("System Class Loader: " + PrintClassLoader.class.getClassLoader());
        System.out.println("Parent: " + PrintClassLoader.class.getClassLoader().getParent());
        System.out.println("===========================");
    }
}
