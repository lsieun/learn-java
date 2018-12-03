package lsieun.test;

public class UserTest {
    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);
        String tmpDir = System.getProperty("java.io.tmpdir");
        System.out.println(tmpDir);
    }
}
