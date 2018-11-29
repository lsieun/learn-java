package lsieun.jvm.classloader.dir;

public class PrintUserCurrentWorkingDirectory {
    public static void main(String[] args) {
        String currentDirectory;
        currentDirectory = System.getProperty("user.dir");
        System.out.println("Current working directory : "+currentDirectory);
    }
}
