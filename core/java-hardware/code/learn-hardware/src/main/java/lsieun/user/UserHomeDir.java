package lsieun.user;

import java.io.File;

public class UserHomeDir {
    public static void main(String[] args) {
        String userHome = System.getProperty("user.home");
        System.out.println(userHome);
        System.out.println(new File(userHome).toURI());
    }
}
