package lsieun.except.java7.try_with_resources;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Note that the close methods of resources are called in the opposite order of their creation.
 */
public class B_CloseOrder {
    public void test() throws IOException {
        try (InputStream in = new FileInputStream("/path/to/file");
             BufferedInputStream bufferedIn = new BufferedInputStream(in);) {
            int value = bufferedIn.read();
        }
    }

    public void test_finally() throws IOException {
        BufferedInputStream bufferedIn = null;
        InputStream in = null;
        try {
            in = new FileInputStream("/path/to/file");
            bufferedIn = new BufferedInputStream(in);
            int value = bufferedIn.read();
        } finally {
            if (bufferedIn != null) {
                try {
                    bufferedIn.close();
                } catch (IOException ex) {
                    // do nothing
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    // do nothing
                }
            }

        }
    }

    public void test_close_quietly() throws IOException {
        BufferedInputStream bufferedIn = null;
        InputStream in = null;
        try {
            in = new FileInputStream("/path/to/file");
            bufferedIn = new BufferedInputStream(in);
            int value = bufferedIn.read();
        } finally {
            closeQuietly(bufferedIn);
            closeQuietly(in);
        }
    }

    public static void closeQuietly(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                // do nothing
            }
        }
    }
}
