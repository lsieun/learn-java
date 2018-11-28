package lsieun.security.permission;

import java.io.FilePermission;
import java.security.Permission;

// https://examples.javacodegeeks.com/core-java/security/check-if-a-permission-implies-another-permission-example/
public class PermissionImplications {
    public static void main(String[] args) {
        String path1 = "/home/*";
        String actions1 = "read,write";
        Permission permission1 = new FilePermission(path1, actions1);

        String path2 = "/home/documents";
        String actions2 = "read";
        Permission permission2 = new FilePermission(path2, actions2);

        if (permission1.implies(permission2)) {

            System.out.println(actions1 + " on " + path1 + " implies " + actions2 + " on " + path2);
        }
    }
}
