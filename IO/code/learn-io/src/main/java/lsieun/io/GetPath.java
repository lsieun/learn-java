package lsieun.io;

import java.io.File;
import java.io.IOException;

public class GetPath {
    public static void main(String[] args) {
        testGetPath();
        testGetAbsolutePath();
        testGetCanonicalPath();
    }

    private static void testGetPath() {
        File file = new File("foo/foo-one.txt");
        String path = file.getPath();
        System.out.println("getPath: " + path);
    }

    private static void testGetAbsolutePath() {
        File file = new File("foo/foo-one.txt");
        String path = file.getAbsolutePath();
        System.out.println("getAbsolutePath: " + path);
    }

    private static void testGetCanonicalPath() {
        File file = new File("bar/baz/../bar-one.txt");

        try {
            String absPath = file.getAbsolutePath();
            String canPath = file.getCanonicalPath();

            System.out.println("getAbsolutePath: " + absPath);
            System.out.println("getCanonicalPath: " + canPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
