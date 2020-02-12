package lsieun.basic.b_url_con;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class A_Instance {
    public static void main(String[] args) {
        try {
            URL u = new URL("http://www.overcomingbias.com/");
            URLConnection uc = u.openConnection();
            // read from the URL...
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
