# SecurityManager

The `java.lang.SecurityManager` class allows **applications** to implement **a security policy**. It **allows an application to determine**, before performing a possibly unsafe or sensitive operation, **what the operation is** and **whether it is being attempted in a security context** that allows the operation to be performed. The application can allow or disallow the operation.

The `java.lang.SecurityManager.checkPermission(Permission perm, Object context)` method throws a `SecurityException` if **the specified security context** is **denied access** to the **resource** specified by **the given permission**. The `context` must be **a security context** returned by a previous call to `getSecurityContext` and **the access control decision** is based upon **the configured security policy** for that **security context**.

## Enable the security manager example

URL: https://examples.javacodegeeks.com/core-java/security/enable-the-security-manager-example/

This is an example of **how to enable the Security Manager in Java**. Since **no security manager is enabled by default**, and **all security checks** to protected resources and operations **are disabled**, **enabling the security manager** implies that you should:

- Create a new `SecurityManager` Object.
- Invoke the `setSecurityManager(SecurityManager s)` API method of the `System`, in order to **enable the new security manager**.
- Invoke the `setProperty(String key, String value)` API method of the `System` (“java.home” and “123456” in this example). The method invocation will throw an `AccessControlException`, since **the security manager** is now enabled and the access to the system property is now not allowed.

```java
package lsieun.security.manager;

import java.security.AccessControlException;

public class EnableSecurityManager {
    public static void main(String[] args) {
        /*
         No security manager is enabled by default.
         Thus all security checks to protected resources and operations are disabled.
         In order to enable security checks, the security manager must be enabled.
        */

        // Security manager is disabled, read/write access to "java.home" system property is allowed
        System.setProperty("java.home", "123456");
        System.out.println("java.home is : " + System.getProperty("java.home"));

        // Enable the security manager
        try {
            SecurityManager securityManager = new SecurityManager();
            System.setSecurityManager(securityManager);
        } catch (SecurityException se) {
            System.out.println("SecurityManager already set");
        }

        try {
            System.setProperty("java.home", "123456");
        } catch (AccessControlException ace) {
            System.out.println("Write access to the java.home system property is not allowed!");
        }

    }
}
```


## More

- [The Java Security Manager: Why and How?](https://dzone.com/articles/the-java-security-manager-why-and-how)






