public class JavaInfo {
    public static final String JAVA_VERSION;
    public static final String JAVA_RUNTIME_VERSION;
    public static final String JAVA_VENDOR;
    public static final boolean isAppleJvm;
    public static final boolean isOracleJvm;
    public static final boolean isSunJvm;
    public static final boolean isIbmJvm;
    public static final boolean isJetBrainsJvm;

    static {
        JAVA_VERSION = System.getProperty("java.version");
        JAVA_RUNTIME_VERSION = getRtVersion(JAVA_VERSION);
        JAVA_VENDOR = System.getProperty("java.vm.vendor", "Unknown");
        isAppleJvm = containsIgnoreCase(JAVA_VENDOR, "Apple");
        isOracleJvm = containsIgnoreCase(JAVA_VENDOR, "Oracle");
        isSunJvm = containsIgnoreCase(JAVA_VENDOR, "Sun") && containsIgnoreCase(JAVA_VENDOR, "Microsystems");
        isIbmJvm = containsIgnoreCase(JAVA_VENDOR, "IBM");
        isJetBrainsJvm = containsIgnoreCase(JAVA_VENDOR, "JetBrains");
    }


    public static void main(String[] args) {
        System.out.println("JAVA_VERSION = " + JAVA_VERSION);
        System.out.println("JAVA_RUNTIME_VERSION = " + JAVA_RUNTIME_VERSION);
        System.out.println("JAVA_VENDOR = " + JAVA_VENDOR);

        System.out.println("isAppleJvm = " + isAppleJvm);
        System.out.println("isOracleJvm = " + isOracleJvm);
        System.out.println("isSunJvm = " + isSunJvm);
        System.out.println("isIbmJvm = " + isIbmJvm);
        System.out.println("isJetBrainsJvm = " + isJetBrainsJvm);
    }

    private static String getRtVersion(String fallback) {
        String rtVersion = System.getProperty("java.runtime.version");
        return Character.isDigit(rtVersion.charAt(0)) ? rtVersion : fallback;
    }

    private static boolean containsIgnoreCase(String where, String what) {
        return indexOfIgnoreCase(where, what, 0) >= 0;
    }

    private static int indexOfIgnoreCase(String where, String what, int fromIndex) {
        int targetCount = what.length();
        int sourceCount = where.length();
        if (fromIndex >= sourceCount) {
            return targetCount == 0 ? sourceCount : -1;
        } else {
            if (fromIndex < 0) {
                fromIndex = 0;
            }

            if (targetCount == 0) {
                return fromIndex;
            } else {
                char first = what.charAt(0);
                int max = sourceCount - targetCount;

                for(int i = fromIndex; i <= max; ++i) {
                    if (!charsEqualIgnoreCase(where.charAt(i), first)) {
                        do {
                            ++i;
                        } while(i <= max && !charsEqualIgnoreCase(where.charAt(i), first));
                    }

                    if (i <= max) {
                        int j = i + 1;
                        int end = j + targetCount - 1;

                        for(int k = 1; j < end && charsEqualIgnoreCase(where.charAt(j), what.charAt(k)); ++k) {
                            ++j;
                        }

                        if (j == end) {
                            return i;
                        }
                    }
                }

                return -1;
            }
        }
    }

    public static boolean charsEqualIgnoreCase(char a, char b) {
        return charsMatch(a, b, true);
    }

    public static boolean charsMatch(char c1, char c2, boolean ignoreCase) {
        return compare(c1, c2, ignoreCase) == 0;
    }

    public static int compare(char c1, char c2, boolean ignoreCase) {
        int d = c1 - c2;
        if (d != 0 && ignoreCase) {
            char u1 = toUpperCase(c1);
            char u2 = toUpperCase(c2);
            d = u1 - u2;
            if (d != 0) {
                d = toLowerCase(u1) - toLowerCase(u2);
            }

            return d;
        } else {
            return d;
        }
    }

    public static char toUpperCase(char a) {
        if (a < 'a') {
            return a;
        } else {
            return a <= 'z' ? (char)(a + -32) : Character.toUpperCase(a);
        }
    }

    public static char toLowerCase(char a) {
        if (a >= 'A' && (a < 'a' || a > 'z')) {
            return a <= 'Z' ? (char)(a + 32) : Character.toLowerCase(a);
        } else {
            return a;
        }
    }

}
