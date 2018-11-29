package lsieun.network.url;

import java.net.URI;
import java.net.URISyntaxException;


public class URIInfo {
    public static void main(String[] args) {
        try {
            URI uri = new URI("https://username:password@www.packtpub.com/books/./content/../support?productId=1234&brandId=5678#bottom_mark");
            displayUI(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void displayUI(URI uri) {


        System.out.println("getScheme: " + uri.getScheme());
        System.out.println("getSchemeSpecificPart: " + uri.getSchemeSpecificPart());
        System.out.println();
        System.out.println("getHost: " + uri.getHost());
        System.out.println("getPath: " + uri.getPath());
        System.out.println("getQuery: " + uri.getQuery());
        System.out.println("getFragment: " + uri.getFragment());
        System.out.println();
        System.out.println("getAuthority: " + uri.getAuthority());
        System.out.println("getUserInfo: " + uri.getUserInfo());
        System.out.println();
        System.out.println("Original : " + uri);
        System.out.println("normalize: " + uri.normalize());
    }
}
