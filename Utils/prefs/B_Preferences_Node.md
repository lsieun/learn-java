# Preferences Node

<!-- TOC -->

- [1. Node Path: relative and absolute](#1-node-path-relative-and-absolute)
- [2. Node Operation](#2-node-operation)
  - [2.1. create node](#21-create-node)
  - [2.2. node existence](#22-node-existence)
  - [2.3. node data](#23-node-data)
  - [2.4. delete node](#24-delete-node)
  - [2.5. persist node](#25-persist-node)
- [3. Change Notification](#3-change-notification)

<!-- /TOC -->

## 1. Node Path: relative and absolute

The `node()` method accepts either **a relative** or **an absolute path**. A relative path asks the node to find the path relative to itself as a base. We also could have gotten our node this way:

```java
Preferences prefs = Preferences.userRoot().node("oreilly").node("learningjava");
```

But `node()` also accepts **an absolute path**, in which case the base node serves only to designate the tree that the path is in. We could use the absolute path `/oreilly/learningjava` as the argument to any `node()` method and reach our preferences object.

## 2. Node Operation

### 2.1. create node

**There is no need to “create” nodes**. When you ask for a node, you get a preferences object for that path in the tree.

### 2.2. node existence

You don’t have to create nodes, but **you can test for the existence of a data node** with the `nodeExists()` method.

### 2.3. node data

Preferences are key-value pairs of data. You can stored a string, int, boolean and other primitive data type. You can use the `get()` method to retrieve value associated with a key from preference node and the `put()` method to store a value associated with a key in the preference node.

### 2.4. delete node

You can remove a node and all its children with the `removeNode()` method. To remove a data item from a node, use the `remove()` method, specifying the key; or you can remove all the data from a node with the `clear()` method (which is not the same as removing the node).

- `removeNode()`: 删除当前节点和子节点
- `remove()`：删除当前节点的一条数据
- `clear()`：删除当前节点的所有数据

### 2.5. persist node

**If you write something to it, that data is eventually placed in persistent storage**, called the **backing store**. The backing store is the implementation-dependent storage mechanism used to hold the preference data. All the `put` methods return immediately, and **no guarantees are made as to when the data is actually stored**.

You can force data to the backing store explicitly using the `flush()` method of the `Preferences` class. Conversely, you can use the `sync()` method to guarantee that a preferences object is up-to-date with respect to changes placed into the backing store by other applications or threads. Both `flush()` and `sync()` throw a `BackingStoreException` if data cannot be read or written for some reason.

## 3. Change Notification

Often your application should be notified if changes are made to the preferences while it’s running. You can get updates on preference changes using the `PreferenceChangeListener` and `NodeChangeListener` interfaces. These interfaces are examples of event listener interfaces.

By registering an object that implements `PreferenceChangeListener` with a node, you can receive updates on **added**, **removed**, and **changed** preference data for that node.

The `NodeChangeListener` allows you to be told **when child nodes are added to or removed** from a specific node.

Here is a snippet that prints all the data changes affecting our `/oreilly/learningjava` node:

```java
Preferences prefs = Preferences.userRoot().node("/oreilly/learningjava");

prefs.addPreferenceChangeListener(new PreferenceChangeListener() {
    public void preferenceChange(PreferenceChangeEvent e) {
        System.out.println("Value: " + e.getKey()
            + " changed to "+ e.getNewValue() );
    }
});
```
