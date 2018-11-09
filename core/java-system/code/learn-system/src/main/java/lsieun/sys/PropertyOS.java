package lsieun.sys;

public class PropertyOS {
    public static void main(String[] args) {
        String os = System.getProperty("os.name");
        boolean isWindows = os.toLowerCase().startsWith("windows");
        System.out.println(isWindows);
    }
}
