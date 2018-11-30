package lsieun.jvm.classloader.dir;

public class PrintJavaHome {
    public static void main(String[] args) {
        String jre = System.getProperty("java.home");
        System.out.println("JAVA_HOME: " + jre);
    }
}
