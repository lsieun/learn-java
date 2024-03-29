package lsieun.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {

    public static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }

    public static void closeQuietly(Closeable c) {
        if(c != null) {
            try {
                c.close();
            } catch (IOException e) {
                // Do Nothing
            }
        }
    }
}
