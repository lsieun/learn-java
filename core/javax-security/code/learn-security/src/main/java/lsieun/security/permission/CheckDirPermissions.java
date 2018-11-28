package lsieun.security.permission;

import java.io.FilePermission;
import java.security.AccessController;

// https://examples.javacodegeeks.com/core-java/security/check-read-write-permission-for-a-directory-example/
public class CheckDirPermissions {
    public static void main(String[] args) {
        String path = "/home/*";
        String actions = "read,write";

        try {
            AccessController.checkPermission(new FilePermission(path, actions));

            System.out.println("You have read/write permition to use : " + path);

        } catch (SecurityException e) {
            System.out.println("NO Permission");
        }
    }
}
