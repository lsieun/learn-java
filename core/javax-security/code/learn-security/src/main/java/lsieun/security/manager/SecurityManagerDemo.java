package lsieun.security.manager;

import java.io.FilePermission;
import java.security.AccessControlContext;
import java.security.AccessController;

public class SecurityManagerDemo extends SecurityManager {
    public static void main(String[] args) {

        // get current security context
        AccessControlContext context = AccessController.getContext();

        // set the policy file as the system securuty policy
        System.setProperty("java.security.policy", "file:///home/liusen/java.policy");

        // create a security manager
        SecurityManagerDemo sm = new SecurityManagerDemo();

        // set the system security manager
        System.setSecurityManager(sm);

        // perform the check
        sm.checkPermission(new FilePermission("test.txt", "read,write"), context);

        // print a message if we passed the check
        System.out.println("Allowed!");
    }
}
