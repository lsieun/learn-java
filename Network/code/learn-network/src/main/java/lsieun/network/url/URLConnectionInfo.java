package lsieun.network.url;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class URLConnectionInfo {
    public static void main(String[] args) {
        try {
            String str = "";
            str = "http://www.java2s.com/Tutorial/Java/0320__Network/AclassthatdisplaysinformationaboutaURL.htm";
            printInfo(new URL(str));
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /** Use the URLConnection class to get info about the URL */
    public static void printInfo(URL url) throws IOException {
        URLConnection c = url.openConnection(); // Get URLConnection from URL
        c.connect(); // Open a connection to URL

        // Display some information about the URL contents
        System.out.println("  Content Type: " + c.getContentType());
        System.out.println("  Content Encoding: " + c.getContentEncoding());
        System.out.println("  Content Length: " + c.getContentLength());
        System.out.println("  Date: " + new Date(c.getDate()));
        System.out.println("  Last Modified: " + new Date(c.getLastModified()));
        System.out.println("  Expiration: " + new Date(c.getExpiration()));

        // If it is an HTTP connection, display some additional information.
        if (c instanceof HttpURLConnection) {
            HttpURLConnection h = (HttpURLConnection) c;
            System.out.println("  Request Method: " + h.getRequestMethod());
            System.out.println("  Response Message: " + h.getResponseMessage());
            System.out.println("  Response Code: " + h.getResponseCode());
        }
    }
}
