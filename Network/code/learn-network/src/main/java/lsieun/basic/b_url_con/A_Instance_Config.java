package lsieun.basic.b_url_con;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class A_Instance_Config {

    public static void main(String[] args) {
        try {
            URL u = new URL("http://www.oreilly.com");
            URLConnection uc = u.openConnection();
            if (!uc.getDoInput()) {
                uc.setDoInput(true);
            }
            // write to the connection...
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
