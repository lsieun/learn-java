package lsieun.network.url;

import java.net.URI;
import java.net.URISyntaxException;

public class URIConstruct {
    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test1() {
        try {
            URI uri = new URI("https://www.packtpub.com/books/content/support");
            System.out.println(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        try {
            URI uri = new URI("https", "en.wiki.org","/wiki/URL_normalization", "Normalization_process");
            System.out.println(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
