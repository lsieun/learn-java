package lsieun.os;

import lsieun.utils.OperatingSystem;

public class OSName {
    /**
     * The key to extract the operating system name from the system properties.
     */
    private static final String OS_KEY = "os.name";

    public static void main(String[] args) {
        String osName = System.getProperty(OS_KEY);
        System.out.println(osName);

        System.out.println(OperatingSystem.getCurrentOperatingSystem());
    }
}
