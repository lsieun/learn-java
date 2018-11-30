package lsieun.jvm.classloader.clazzpath;

public class PrintEnvClassPath {
    public static void main(String[] args) {
        String property = System.getProperty("env.class.path");
        System.out.println(property);
    }
}
