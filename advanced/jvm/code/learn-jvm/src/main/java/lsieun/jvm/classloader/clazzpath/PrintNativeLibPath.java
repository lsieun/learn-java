package lsieun.jvm.classloader.clazzpath;

public class PrintNativeLibPath {
    public static void main(String[] args) {
        String property = System.getProperty("java.library.path");
        String[] array = property.split(":");
        for (String item : array) {
            System.out.println(item);
        }
    }
}
