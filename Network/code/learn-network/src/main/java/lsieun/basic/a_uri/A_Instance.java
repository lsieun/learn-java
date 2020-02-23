package lsieun.basic.a_uri;

import java.net.URI;

public class A_Instance {
    public static void main(String[] args) {
        // url: ftp://ftp.oreilly.com
        // username: anonymous
        // password: elharo@ibiblio.org
        URI uri = URI.create("ftp://anonymous:elharo%40ibiblio.org@ftp.oreilly.com:21/pub/stylesheet");
        System.out.println(uri.toString());
    }
}
