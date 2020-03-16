package lsieun.utils.prefs;

import java.util.prefs.Preferences;

public class B_Preferences_For_Classes {
    public static void main(String[] args) {
        Preferences myPrefs = Preferences.userNodeForPackage(B_Preferences_For_Classes.class);
        myPrefs.put("username", "tomcat");
    }
}
