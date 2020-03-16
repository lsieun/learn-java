import java.util.Locale;

/**
 * @see com.intellij.openapi.util.SystemInfoRt
 */
public class SystemInfo {
    public static final String OS_NAME = System.getProperty("os.name");
    private static final String _OS_NAME;
    public static final String OS_VERSION;
    public static final String OS_ARCH;
    public static final boolean isWindows;
    public static final boolean isMac;
    public static final boolean isLinux;
    public static final boolean isFreeBSD;
    public static final boolean isSolaris;
    public static final boolean isUnix;
    public static final boolean isFileSystemCaseSensitive;

    public SystemInfo() {
    }

    static {
        _OS_NAME = OS_NAME.toLowerCase(Locale.US);
        OS_VERSION = System.getProperty("os.version").toLowerCase(Locale.US);
        OS_ARCH = System.getProperty("os.arch");
        isWindows = _OS_NAME.startsWith("windows");
        isMac = _OS_NAME.startsWith("mac");
        isLinux = _OS_NAME.startsWith("linux");
        isFreeBSD = _OS_NAME.startsWith("freebsd");
        isSolaris = _OS_NAME.startsWith("sunos");
        isUnix = !isWindows;
        isFileSystemCaseSensitive = isUnix && !isMac;
    }

    public static void main(String[] args) {
        System.out.println("OS_NAME = " + OS_NAME);
        System.out.println("_OS_NAME = " + _OS_NAME);
        System.out.println("OS_VERSION = " + OS_VERSION);
        System.out.println("OS_ARCH = " + OS_ARCH);
        System.out.println("isWindows = " + isWindows);
        System.out.println("isMac = " + isMac);
        System.out.println("isLinux = " + isLinux);
        System.out.println("isFreeBSD = " + isFreeBSD);
        System.out.println("isSolaris = " + isSolaris);
        System.out.println("isUnix = " + isUnix);
        System.out.println("isFileSystemCaseSensitive = " + isFileSystemCaseSensitive);
    }
}
