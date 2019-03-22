public class ReadBooleanFromCommandLine {
    private static final boolean debug = Boolean.getBoolean("JavaClass.debug");

    public static void main(String[] args) {
        System.out.println("Hello World");
        Debug("Hello Java");
    }

    public static void Debug(final String str) {
        if(debug) {
            System.out.println(str);
        }
    }
}
