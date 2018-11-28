package lsieun.security.permission;

import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Policy;
import java.security.ProtectionDomain;
import java.util.Enumeration;

// https://examples.javacodegeeks.com/core-java/security/get-all-permissions-granted-to-a-loaded-class-example/
public class GetGrantedPermissions {
    public static void main(String[] args) {
        // Get the protection domain for the class
        ProtectionDomain protectionDomain = GetGrantedPermissions.class.getProtectionDomain();

        // Get all the permissions from the Policy object
        PermissionCollection permissionCollection = Policy.getPolicy().getPermissions(protectionDomain);

        Enumeration permissions = permissionCollection.elements();
        while (permissions.hasMoreElements()) {

            Permission permission = (Permission)permissions.nextElement();

            System.out.println(permission.getName());
        }
    }
}
