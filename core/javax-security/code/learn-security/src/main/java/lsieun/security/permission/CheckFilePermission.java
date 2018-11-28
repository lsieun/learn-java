package lsieun.security.permission;

import java.io.FilePermission;
import java.security.AccessControlException;
import java.security.AccessController;

// https://examples.javacodegeeks.com/core-java/security/accesscontroller-example/
public class CheckFilePermission {
    public static void main(String args[]) {
        FilePermission fp = new FilePermission("/home/liusen/output.txt", "read");
        try {
            AccessController.checkPermission(fp);
        } catch (AccessControlException ex) {
            System.out.println("Access denied");
        }
    }
}
