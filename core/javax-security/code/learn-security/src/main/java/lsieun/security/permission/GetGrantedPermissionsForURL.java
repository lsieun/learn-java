package lsieun.security.permission;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Policy;
import java.security.cert.Certificate;
import java.util.Enumeration;

// https://examples.javacodegeeks.com/core-java/security/get-all-permissions-granted-to-classes-loaded-from-a-specific-url/
public class GetGrantedPermissionsForURL {
    public static void main(String[] args) {
        URL codebase = null;

        try {
            // Get permissions for a URL (example)
            // codebase = new URL("http://www.javacodegeeks.com/");
            // Get permissions for a directory
            codebase = new File(System.getProperty("user.home")).toURL();
        } catch (IOException e) {
        }

        // Construct a code source from the code base
        CodeSource codeSource = new CodeSource(codebase, new Certificate[] {});

        // Get all granted permissions
        PermissionCollection permissionCollection = Policy.getPolicy().getPermissions(codeSource);

        Enumeration permissions = permissionCollection.elements();
        while (permissions.hasMoreElements()) {
            Permission permission = (Permission) permissions.nextElement();
            System.out.println(permission.getName());
        }
    }
}
