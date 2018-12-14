package lsieun.user;

import java.io.File;

public class UserWorkingDir {
    public static void main(String[] args) {
        String workingDir = System.getProperty("user.dir");
        System.out.println(workingDir);
        System.out.println(new File(workingDir).toURI());
    }
}
