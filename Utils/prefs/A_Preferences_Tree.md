# Preferences Tree

<!-- TOC -->

- [1. Stored logically in a tree](#1-stored-logically-in-a-tree)
- [2. Two separate trees](#2-two-separate-trees)

<!-- /TOC -->

## 1. Stored logically in a tree

**Preferences are stored logically in a tree**. A preferences object is a **node** in the **tree** located by a unique path<sub>从抽象的角度来说，它是树中的一个节点。</sub>. You can think of preferences as files in a directory structure<sub>从目录结构的角度来说，它是一个文件</sub>; within the file are stored one or more `name/value` pairs. To store or retrieve items, you ask for a preferences object for the correct path.

Here is an example; we’ll explain the node lookup shortly:

```java
Preferences prefs = Preferences.userRoot().node("oreilly/learningjava");

prefs.put("author", "Niemeyer");
prefs.putInt("edition", 4);

String author = prefs.get("author", "unknown");
int edition = prefs.getInt("edition", -1);
```

In addition to the `String` and `int` type accessors, there are the following `get` methods for other types: `getLong()`, `getFloat()`, `getDouble()`, `getByteArray()`, and `getBoolean()`. Each of these `get` methods takes** a key name** and **default value** to be used if no value is defined. And, of course, for each `get` method, there is a corresponding “`put`” method that takes **the name** and **a value of the corresponding type**. Providing defaults in the `get` methods is mandatory. The intent is for applications to function even if there is no preference information or if the storage for it is not available.

## 2. Two separate trees

**Preferences are stored in two separate trees**: **system preferences** and **user preferences**. **System preferences** are shared by all users of the Java installation. But **user preferences** are maintained separately for each user; each user sees his or her own preference information.

```java
Preferences prefs = Preferences.userRoot().node("oreilly/learningjava");
```

In our example, we used the static method `userRoot()` to fetch the root node (preference object) for the user preferences tree. We then asked that node to find the child node at the path `oreilly/learningjava`, using the `node()` method. The corresponding `systemRoot()` method provides the system root node.
