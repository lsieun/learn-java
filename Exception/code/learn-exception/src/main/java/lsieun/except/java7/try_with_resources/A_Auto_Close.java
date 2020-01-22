package lsieun.except.java7.try_with_resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class A_Auto_Close {
    public void basic() throws IOException {
        InputStream in = new FileInputStream("/path/to/file");
        int b = in.read();
    }

    public void test_try_finally() throws IOException {
        InputStream in = null;
        try {
            in = new FileInputStream("/path/to/file");
            int b = in.read();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                // do nothing
            }
        }
    }

    public void test_try_with_resources() throws IOException {
        try (InputStream in = new FileInputStream("/path/to/file");) {
            int b = in.read();
        }
    }

    public void test_no_try_with_resources() throws IOException {
        InputStream in = null; // 1
        Throwable primaryException = null; // 2

        try {
            in = new FileInputStream("/path/to/file");
            int b = in.read(); // 3
        } catch (Throwable ex) { // 3
            primaryException = ex;
            throw ex;
        } finally {
            if (in != null) {
                if (primaryException != null) {
                    try {
                        in.close();
                    } catch (Throwable suppressedException) {
                        primaryException.addSuppressed(suppressedException);
                    }
                } else {
                    in.close();
                }
            }
        }
    }

}
