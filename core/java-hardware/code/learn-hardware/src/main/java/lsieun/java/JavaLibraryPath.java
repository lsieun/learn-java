package lsieun.java;

public class JavaLibraryPath {
    public static void main(String[] args) {
        String libPath = System.getProperty("java.library.path");
        System.out.println(libPath);
    }
}
