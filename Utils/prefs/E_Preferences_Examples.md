# Java Preferences API

`java.util.prefs.Preferences` can be easily used. You have to **define a node** in which the data is stored. Then you can **call the getter and setter methods**. The second value is the default value, e.g. if the preference value is not set yet, then this value will be used.

Create the following program.

```java
import java.util.prefs.Preferences;

public class PreferenceTest {
  private Preferences prefs;

  public void setPreference() {
    // This will define a node in which the preferences can be stored
    // 下面的数据存储在了~/.java/.userPrefs/<nodename>/prefs.xml
    // 在这里<nodename>是liusen-prefs-test
    prefs = Preferences.userRoot().node("liusen-prefs-test");
    String ID1 = "Test1";
    String ID2 = "Test2";
    String ID3 = "Test3";

    // First we will get the values
    // Define a boolean value
    System.out.println(prefs.getBoolean(ID1, true));
    // Define a string with default "Hello World
    System.out.println(prefs.get(ID2, "Hello World"));
    // Define a integer with default 50
    System.out.println(prefs.getInt(ID3, 50));

    // now set the values
    prefs.putBoolean(ID1, false);
    prefs.put(ID2, "Hello Europa");
    prefs.putInt(ID3, 45);

    // Delete the preference settings for the first value
    prefs.remove(ID1);

  }

  public static void main(String[] args) {
    PreferenceTest test = new PreferenceTest();
    test.setPreference();
  }
}
```
