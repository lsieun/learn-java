package lsieun.java;

public class JavaExtDir {
    public static void main(String[] args) {
        String extDir = System.getProperty("java.ext.dirs");
        System.out.println(extDir);
    }
}
