package lsieun.basic.c_http_url_con;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class A_Instance {
    public static void main(String[] args) {
        try {
            URL u = new URL("http://lesswrong.com/");
            URLConnection uc = u.openConnection();
            HttpURLConnection http = (HttpURLConnection) uc;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
