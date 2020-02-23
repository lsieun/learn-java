package lsieun.basic.proxy;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class HttpUtils {
    public static void connect(String url) {
        connect(url, null);
    }

    public static void connect(String url, Proxy proxy) {
        try {
            URL u = new URL(url);
            HttpURLConnection uc = null;
            if(proxy == null) {
                uc = (HttpURLConnection) u.openConnection();
            }else {
                uc = (HttpURLConnection) u.openConnection(proxy);
            }


            uc.setRequestProperty("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.87 Safari/537.36");
            uc.setRequestProperty("accept-language", "en-US,en;q=0.9,zh-TW;q=0.8,zh-CN;q=0.7,zh;q=0.6");
            uc.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");

            Map<String, List<String>> request_headers = uc.getRequestProperties();
            for (Map.Entry<String, List<String>> item : request_headers.entrySet()) {
                System.out.println(item.getKey() + ": " + item.getValue());
            }
            System.out.println();

            // connect the url
            uc.connect();

            // message status line
            System.out.println(uc.getHeaderField(0));

            // message header
            for (int i = 1; ; i++) {
                String header = uc.getHeaderField(i);
                if (header == null) break;
                System.out.println(uc.getHeaderFieldKey(i) + ": " + header);
            }
            System.out.println();

            // message body
            try (InputStream raw = uc.getInputStream()) { // auto close
                InputStream buffer = new BufferedInputStream(raw);
                Reader reader = new InputStreamReader(buffer, StandardCharsets.UTF_8);
                int c;
                while ((c = reader.read()) != -1) {
                    System.out.print((char) c);
                }
            }
        } catch (MalformedURLException ex) {
            System.err.println(url + " is not a parseable URL");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
