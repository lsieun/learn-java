package lsieun.basic.a_url;

import java.net.MalformedURLException;
import java.net.URL;

public class A_Instance {
    public static void main(String[] args) {
        try {
            URL u1 = new URL("http://www.audubon.org/");

            /**
             * The constructor sets the port to -1 so the default port for the protocol will be used.
             *
             * The file argument should begin with a slash and include a path, a filename, and optionally
             * a fragment identifier. Forgetting the initial slash is a common mistake, and one that is
             * not easy to spot.
             */
            URL u2 = new URL("http", "www.eff.org", "/blueribbon.html#intro");

            URL u3 = new URL("http", "fourier.dur.ac.uk", 8000, "/~dma3mjh/jsci/");

            /**
             * The constructor computes the new URL as http://www.ibiblio.org/javafaq/mailinglists.html.
             */
            URL u4 = new URL("http://www.ibiblio.org/javafaq/index.html");
            URL u5 = new URL (u4, "mailinglists.html");
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        }
    }
}
