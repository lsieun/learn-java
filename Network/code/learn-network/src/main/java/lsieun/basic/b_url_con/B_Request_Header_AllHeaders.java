package lsieun.basic.b_url_con;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class B_Request_Header_AllHeaders {
    public static void main(String[] args) {
        String url = "http://www.example.com";
        try {
            URL u = new URL(url);
            URLConnection uc = u.openConnection();
            for (int i = 1; ; i++) {
                String header = uc.getHeaderField(i);
                if (header == null) break;
                System.out.println(uc.getHeaderFieldKey(i) + ": " + header);
            }
        } catch (MalformedURLException ex) {
            System.err.println(url + " is not a URL I understand.");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        System.out.println();
    }
}
