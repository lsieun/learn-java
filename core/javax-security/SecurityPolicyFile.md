# Policy File

URL: 

- https://dzone.com/articles/the-java-security-manager-why-and-how
- https://docs.oracle.com/javase/8/docs/technotes/guides/security/PolicyFiles.html

## 1. activate the `SecurityManager`

In order to activate the `SecurityManager`, just launch the **JVM** with the `java.security.manager` system property i.e. 

```
java -Djava.security.manager
``` 

At this point, the JVM will use **the default JRE policy**. It’s configured in the file located at `JAVA_HOME/lib/security/java.policy` (for Java 8).

Here’s a sample of this file:

```txt
grant codeBase "file:${{java.ext.dirs}}/*" {
        permission java.security.AllPermission;
};
grant {
        permission java.lang.RuntimePermission "stopThread";
        permission java.net.SocketPermission "localhost:0", "listen";
        permission java.util.PropertyPermission "java.version", "read";
        permission java.util.PropertyPermission "java.vendor", "read";
        ...
}
```

**The first section** – `grant codeBase`, is about which code can be executed; **the second section** – `grant`, is about **specific permissions**.

## 2. Default Policy File Locations

Default Policy:

- The **system** policy file
- The **user** policy file

The **system** policy file is by default located at

```txt
java.home/lib/security/java.policy  (Solaris, Linux, or Mac OS X)
java.home\lib\security\java.policy  (Windows)
```

Note: `java.home` refers to the value of **the system property** named "`java.home`", which specifies the directory that houses the runtime environment -- either the jre directory in the Java SE Development Kit (JDK) or the top-level directory of the Java SE Runtime Environment (JRE).

The **user** policy file is by default located at

```txt
user.home/.java.policy  (Solaris, Linux, or Mac OS X)
user.home\.java.policy  (Windows)
```

Note: `user.home` refers to the value of the system property named "`user.home`", which specifies the user's home directory.


## 3. Using an Alternate `java.policy` File

分为两种情况：

- add (使用`=`)
- replace (使用`==`)

To **add** another policy file in addition to the default JRE’s, thus adding more permissions, launch the JVM with:

```bash
java -Djava.security.manager -Djava.security.policy=/path/to/other.policy
```

To **replace** the default policy file with your own, launch the JVM with:

```bash
java -Djava.security.manager -Djava.security.policy==/path/to/other.policy
```

Note: the **double equal sign**.

## 4. Configuring Your Own Policy File

Security configuration can be either based on a:

### 4.1 Black list

In a **black list** scenario, everything is allowed but exceptions can be configured to disallow some operations.

### 4.2 White list

On the opposite, in a **white list** scenario, only operations that are explicitly configured are allowed. **By default, all operations are disallowed**.

If you want to create your own policy file, it’s suggested you start with **a blank one** and then launch your app. As soon, as you get **a security exception**, add **the necessary permission** is the policy. Repeat until you have all necessary permissions. Following this process will let you have only the minimal set of permissions to run the application, thus implementing **the least privilege security principle**.

Note that if you’re using a container or a server, you’ll probably require a lot of those permissions, but this is the price to pay to secure your JVM against abuses.

## 5. Full `java.policy` File (Java 8)

File: `/usr/local/jdk1.8.0_181/jre/lib/security/java.policy` (Fedora)

```txt

// Standard extensions get all permissions by default

grant codeBase "file:${{java.ext.dirs}}/*" {
        permission java.security.AllPermission;
};

// default permissions granted to all domains

grant {
        // Allows any thread to stop itself using the java.lang.Thread.stop()
        // method that takes no argument.
        // Note that this permission is granted by default only to remain
        // backwards compatible.
        // It is strongly recommended that you either remove this permission
        // from this policy file or further restrict it to code sources
        // that you specify, because Thread.stop() is potentially unsafe.
        // See the API specification of java.lang.Thread.stop() for more
        // information.
        permission java.lang.RuntimePermission "stopThread";

        // allows anyone to listen on dynamic ports
        permission java.net.SocketPermission "localhost:0", "listen";

        // "standard" properies that can be read by anyone

        permission java.util.PropertyPermission "java.version", "read";
        permission java.util.PropertyPermission "java.vendor", "read";
        permission java.util.PropertyPermission "java.vendor.url", "read";
        permission java.util.PropertyPermission "java.class.version", "read";
        permission java.util.PropertyPermission "os.name", "read";
        permission java.util.PropertyPermission "os.version", "read";
        permission java.util.PropertyPermission "os.arch", "read";
        permission java.util.PropertyPermission "file.separator", "read";
        permission java.util.PropertyPermission "path.separator", "read";
        permission java.util.PropertyPermission "line.separator", "read";

        permission java.util.PropertyPermission "java.specification.version", "read";
        permission java.util.PropertyPermission "java.specification.vendor", "read";
        permission java.util.PropertyPermission "java.specification.name", "read";

        permission java.util.PropertyPermission "java.vm.specification.version", "read";
        permission java.util.PropertyPermission "java.vm.specification.vendor", "read";
        permission java.util.PropertyPermission "java.vm.specification.name", "read";
        permission java.util.PropertyPermission "java.vm.version", "read";
        permission java.util.PropertyPermission "java.vm.vendor", "read";
        permission java.util.PropertyPermission "java.vm.name", "read";
};

```

