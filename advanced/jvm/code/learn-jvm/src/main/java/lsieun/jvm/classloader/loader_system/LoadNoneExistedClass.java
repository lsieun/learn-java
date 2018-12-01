package lsieun.jvm.classloader.loader_system;

public class LoadNoneExistedClass {
    public static void main(String[] args) {
        try {
            Class.forName("lsieun.jvm.SimpleClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
