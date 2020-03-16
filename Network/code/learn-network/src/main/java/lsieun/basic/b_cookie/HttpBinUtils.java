package lsieun.basic.b_cookie;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpBinUtils {
    public static void connect(final String url) {
        System.out.printf("%s%n%n", url);

        try {
            URL u = new URL(url);
            HttpURLConnection http = (HttpURLConnection) u.openConnection();
            http.setRequestProperty("User-Agent", "Mozilla/5.0");
            http.setRequestProperty("Host", "httpbin.org");
            http.setRequestProperty("Referer", "http://httpbin.org/");
            http.setRequestProperty("Accept", "text/html");
            http.setRequestProperty("Connection", "close");

            http.connect();
            System.out.println(http.getHeaderField(0));
            for (int i=1;;i++) {
                String key = http.getHeaderFieldKey(i);
                String value = http.getHeaderField(i);
                if (value == null) break;
                System.out.printf("%s: %s%n", key, value);
            }
            System.out.println();
            InputStream in = http.getInputStream();
            in = new BufferedInputStream(in);
            for (int ch = in.read(); ch != -1; ch = in.read()) {
                System.out.printf("%c", ch);
            }
            System.out.println();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
