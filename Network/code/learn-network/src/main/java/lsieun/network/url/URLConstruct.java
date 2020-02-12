package lsieun.network.url;

import java.net.MalformedURLException;
import java.net.URL;

public class URLConstruct {
    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test1() {
        try {
            URL url = new URL("http://www.packtpub.com");
            System.out.println(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        try {
            URL url = new URL("http", "pluto.jhuapl.edu", 80, "/New-Center/index.php");
            System.out.println(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
