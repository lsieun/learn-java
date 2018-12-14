package lsieun.java;

public class JavaPreferencesFactory {
    public static void main(String[] args) {
        String factory = System.getProperty("java.util.prefs.PreferencesFactory");
        System.out.println(factory);
    }
}
