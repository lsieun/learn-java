package lsieun.jaas.sample;

import java.io.File;
import java.security.PrivilegedAction;

public class SampleAction implements PrivilegedAction {
    public Object run() {
        System.out.println("\nYour java.home property: " + System.getProperty("java.home"));

        System.out.println("\nYour user.home property: " + System.getProperty("user.home"));

        File f = new File("foo.txt");
        System.out.print("\nfoo.txt does ");
        if (!f.exists()){
            System.out.print("not ");
        }
        System.out.println("exist in the current working directory.");

        return null;
    }
}
