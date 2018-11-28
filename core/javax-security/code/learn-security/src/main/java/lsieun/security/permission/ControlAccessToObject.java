package lsieun.security.permission;

import java.security.AccessControlException;
import java.security.Guard;
import java.security.GuardedObject;
import java.util.PropertyPermission;

// https://examples.javacodegeeks.com/core-java/security/control-access-to-an-object-example/
public class ControlAccessToObject {
    public static void main(String[] args) {

        // The object that requires protection
        String password = new String("my_password");

        /* The permission that will protect the object. In this case everyone (thread)
        who has read access to the "java.home" environment variable can
        access the object as well
        */
        Guard guard = new PropertyPermission("java.home", "read");

        // Create the guard
        GuardedObject guardedObject = new GuardedObject(password, guard);

        // Get the guarded object, only threads with the required permission can access the object.
        try {
            password = (String) guardedObject.getObject();
            System.out.println("Protected object is " + password);

        } catch (AccessControlException e) {
            System.out.println("Cannot access the object - permission is denied");
        }

    }
}
