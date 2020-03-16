# Preferences Storage



Although the details of the backing store are implementation-dependent, the `Preferences` API provides a simple import/export facility that can read and write parts of a preference tree to an XML file. (The format for the file is available at `http://www.oracle.com/webfolder/technetwork/jsc/dtd/preferences.dtd`.) A preference object can be written to an output stream with the `exportNode()` method. The `exportSubtree()` method writes the node and all its children. Going the other way, the static `Preferences.importPreferences()` method can read the XML file and populate the appropriate tree with its data. The XML file records whether it is user or system preferences, but user data is always placed into the current user’s tree, regardless of who generated it.

It’s interesting to note that because the import mechanism writes directly to the tree, you can’t use this as a general data-to-XML storage mechanism (other APIs play that role). Also, although we said that the implementation details are not specified, it’s interesting how things really work in the current implementation. On some systems, Java creates a directory hierarchy for each tree at `$JAVA_HOME/jre/.systemPrefs` and `$HOME/.java/.userPrefs`, respectively. In each directory, there is an XML file called `prefs.xml` corresponding to that node.

## Where is the default backing store?

System and user preference data is stored persistently in an implementation-dependent backing store. Typical implementations include flat files, OS-specific registries, directory servers and SQL databases.

> 笔记：在不同的操作系统上，实现方式是不同的。

For example, on Windows systems the data is stored in the Windows registry.

> 笔记：在Windows操作系统上，数据存储在注册表当中。

On Linux systems, the **system preferences** are typically stored at `java-home/.systemPrefs` in a network installation, or `/etc/.java/.systemPrefs` in a local installation. If both are present, `/etc/.java/.systemPrefs` takes precedence. The system preferences location can be overridden by setting the system property `java.util.prefs.systemRoot`. The **user preferences** are typically stored at `user-home/.java/.userPrefs`. The user preferences location can be overridden by setting the system property `java.util.prefs.userRoot`.

- Linux System
  - system preferences (`java.util.prefs.systemRoot`)
    - network installation: `java-home/.systemPrefs`
    - local installation: `/etc/.java/.systemPrefs`
  - user preferences (`java.util.prefs.userRoot`)
    - `user-home/.java/.userPrefs`
