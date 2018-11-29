package lsieun.jvm.classloader.clazzpath;

public class PrintUserClassPath {
    public static void main(String[] args) {
        String property = System.getProperty("java.class.path");
        String[] array = property.split(":");
        for (String item : array) {
            System.out.println(item);
        }

    }
}
