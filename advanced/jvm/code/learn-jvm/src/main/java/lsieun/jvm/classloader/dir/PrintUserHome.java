package lsieun.jvm.classloader.dir;

public class PrintUserHome {
    public static void main(String[] args) {
        String currentDirectory;
        currentDirectory = System.getProperty("user.home");
        System.out.println("Current working directory : "+currentDirectory);
    }
}
