package lsieun.jvm.classloader.clazzpath;

public class PrintBootClassPath {
    public static void main(String[] args) {
        String property = System.getProperty("sun.boot.class.path");
        String[] array = property.split(":");
        for (String item : array) {
            System.out.println(item);
        }

    }
}
