package lsieun.jvm.classloader.clazzpath;

public class PrintExtClassPath {
    public static void main(String[] args) {
        String property = System.getProperty("java.ext.dirs");
        String[] array = property.split(":");
        for (String item : array) {
            System.out.println(item);
        }
    }
}
