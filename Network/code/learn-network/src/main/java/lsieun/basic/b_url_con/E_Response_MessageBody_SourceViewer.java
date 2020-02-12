package lsieun.basic.b_url_con;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class E_Response_MessageBody_SourceViewer {
    public static void main(String[] args) {
        String url = "http://www.example.com";
        try {
            URL u = new URL(url);
            URLConnection uc = u.openConnection();
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
