package lsieun.java;

public class JavaRuntime {
    public static void main(String[] args) {
        String name = System.getProperty("java.runtime.name");
        String version = System.getProperty("java.runtime.version");
        System.out.println(name);
        System.out.println(version);
    }
}
