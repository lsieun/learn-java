# Java Preferences API

URL: http://www.vogella.com/tutorials/JavaPreferences/article.html

## 1. Java Preferences API

The **Java Preferences API** provides a systematic way to **handle program preference configurations**, e.g. to save user settings, remember the last value of a field etc.

`Preferences` are **key/values** pairs where the **key** is an arbitrary name for the preference. The **value** can be a `boolean`, `string`, `int` of another primitive type. `Preferences` are received and saved by `get` and `put` methods while the `get` methods also supply **a default value** in case the `preferences` is not yet set.

> This Java Preferences API is not indented to save application data, i.e. larger structured data.  
> 为什么要说这一段话呢？Java Preferences API主要是用来存储用户对于程序的某种“偏好数据”（用户的设置／上一次编辑的值），而并不适合于存储程序的业务数据。


The **Java Preference API** removes the burden from the individual programmer to write code to save configuration values on the different platforms his program may be running.

> Java Preference API是与操作系统无关的，但是（接下段）

The actual storage of the data is dependent on the platform.

> 真实的数据存储是依赖于操作系统的，Java Preference API对此做了抽象。

## 2. Exercise: Using the API

### 2.1. Create program

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

### 2.2. Validate

Run the program twice. The value of "ID1" should be written with its default value (true) to the command line, as the preference value was deleted at the end of the method. The value of "ID2" and "ID3" should have changed after the first call.



