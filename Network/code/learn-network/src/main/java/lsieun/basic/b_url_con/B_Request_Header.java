package lsieun.basic.b_url_con;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class B_Request_Header {
    public static void main(String[] args) {
        String url = "http://www.example.com";
        try {
            URL u = new URL(url);
            URLConnection uc = u.openConnection();
            uc.setRequestProperty("user-agent", "Chrome");
            Map<String, List<String>> request_headers = uc.getRequestProperties();
            for (Map.Entry<String, List<String>> item : request_headers.entrySet()) {
                System.out.println(item.getKey() + ": " + item.getValue());
            }

        } catch (MalformedURLException ex) {
            System.err.println(url + " is not a URL I understand.");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        System.out.println();
    }
}
