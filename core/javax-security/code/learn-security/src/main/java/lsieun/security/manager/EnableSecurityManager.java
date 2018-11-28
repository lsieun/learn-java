package lsieun.security.manager;

import java.security.AccessControlException;

// https://examples.javacodegeeks.com/core-java/security/enable-the-security-manager-example/
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
