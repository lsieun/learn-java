package lsieun.os;

public class OSVersion {
    public static void main(String[] args) {
        String version = System.getProperty("os.version");
        System.out.println(version);
    }
}
