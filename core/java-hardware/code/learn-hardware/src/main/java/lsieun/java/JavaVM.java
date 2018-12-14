package lsieun.java;

public class JavaVM {
    public static void main(String[] args) {
        String name = System.getProperty("java.vm.name");
        String info = System.getProperty("java.vm.info");
        String version = System.getProperty("java.vm.version");
        System.out.println(name);
        System.out.println(info);
        System.out.println(version);
    }
}
