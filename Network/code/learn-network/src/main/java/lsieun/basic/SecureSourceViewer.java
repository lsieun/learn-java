package lsieun.basic;

import java.io.*;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.URL;

public class SecureSourceViewer {
    public static void main(String[] args) {
        Authenticator.setDefault(new DialogAuthenticator());
        String url = "http://httpbin.org/basic-auth/tom/cat";

        try {
            // Open the URL for reading
            URL u = new URL(url);
            try (InputStream in = new BufferedInputStream(u.openStream())) {
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

        // print a blank line to separate pages
        System.out.println();

        // Since we used the AWT, we have to explicitly exit.
        System.exit(0);
    }
}
