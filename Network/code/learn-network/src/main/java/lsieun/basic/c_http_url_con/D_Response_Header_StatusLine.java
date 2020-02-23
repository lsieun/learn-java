package lsieun.basic.c_http_url_con;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class D_Response_Header_StatusLine {
    public static void main(String[] args) {
        String url = "http://httpbin.org";
        try {
            // Open the URLConnection for reading
            URL u = new URL(url);
            HttpURLConnection uc = (HttpURLConnection) u.openConnection();

            int code = uc.getResponseCode();
            String response = uc.getResponseMessage();
            System.out.println("HTTP/1.x " + code + " " + response);
            System.out.println();

            System.out.println(uc.getHeaderField(0));
            for (int j = 1; ; j++) {
                String header = uc.getHeaderField(j);
                String key = uc.getHeaderFieldKey(j);
                if (header == null || key == null) break;
                System.out.println(uc.getHeaderFieldKey(j) + ": " + header);
            }
            System.out.println();

            try (InputStream in = new BufferedInputStream(uc.getInputStream())) {
                // chain the InputStream to a Reader
                Reader r = new InputStreamReader(in);
                int c;
                while ((c = r.read()) != -1) {
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
