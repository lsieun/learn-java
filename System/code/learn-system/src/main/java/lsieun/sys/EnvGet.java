package lsieun.sys;

public class EnvGet {
    public static void main(String[] args) {
        String path = System.getenv("PATH");
        System.out.println(path);
        String javaHome = System.getenv("JAVA_HOME");
        System.out.println(javaHome);
    }
}
