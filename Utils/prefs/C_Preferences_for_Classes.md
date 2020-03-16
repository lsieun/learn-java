# Preferences for Classes

Java is an object-oriented language, and so it’s natural to wish to associate **preference data** with **classes**. The Preferences API follows this pattern by associating **a node** with **each Java package**. Its convention is simple: **the node path** is just the package name with the dots (`.`) converted to slashes (`/`). **All classes in the package share the same node**.

You can get the preference object node for a class using the static `Preferences.userNodeForPackage()` or `Preferences.systemNodeForPackage()` methods, which take a `Class` as an argument and return the corresponding package node for the user and system trees, respectively. For example:

```java
Preferences datePrefs = Preferences.systemNodeForPackage(Date.class);

Preferences myPrefs = Preferences.userNodeForPackage(MyClass.class);
Preferences morePrefs = Preferences.userNodeForPackage(myObject.getClass());
```
