package lsieun.network.url;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class URL2URI {
    public static void main(String[] args) {
        info("https://lsieun.cn");
        info("ftp://speedtest.tele2.net/");
        info("file:///home/liusen/myself.md");
        info("urn://www.baidu.om");
    }

    public static void info(String str) {
        System.out.println("STR: " + str);
        try {
            URL url = new URL(str);
            System.out.println("\tURL: " + url);
            URI uri = url.toURI();
            System.out.println("\tURI: " + uri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println("=========================");
    }
}
