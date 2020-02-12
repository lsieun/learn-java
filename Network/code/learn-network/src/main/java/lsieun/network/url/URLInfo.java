package lsieun.network.url;

import java.net.MalformedURLException;
import java.net.URL;

public class URLInfo {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://username:pass@www.lsieun.cn:80/category/book/math?bookId=123&isbn=456#top_bookmark");
            //URL url = new URL("file:///home/liusen/myself.md");
            displayURL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void displayURL(URL url) {
        System.out.println("URL: " + url);
        System.out.println("\tProtocol: " + url.getProtocol());
        System.out.println("\tHost: " + url.getHost());
        System.out.println("\tPort: " + url.getPort());
        System.out.println("\tDefaultPort: " + url.getDefaultPort());
        System.out.println("\tPath: " + url.getPath());
        System.out.println("\tQuery: " + url.getQuery());
        System.out.println("\tFile: " + url.getFile());
        System.out.println("\tReference: " + url.getRef());
        System.out.println("\tAuthority: " + url.getAuthority());
        System.out.println("\tUserInfo: " + url.getUserInfo());

    }
}
