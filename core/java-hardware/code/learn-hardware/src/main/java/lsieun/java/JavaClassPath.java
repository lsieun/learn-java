package lsieun.java;

public class JavaClassPath {
    public static void main(String[] args) {
        String classpath = System.getProperty("java.class.path");
        System.out.println(classpath);
    }
}
