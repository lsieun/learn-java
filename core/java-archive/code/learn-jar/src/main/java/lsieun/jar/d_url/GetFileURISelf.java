package lsieun.jar.d_url;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class GetFileURISelf {
    public static void main(String[] args) {
        try {
            URL url = GetFileURISelf.class.getResource("/text/readme.txt");
            URI uri = url.toURI();
            File file = new File(uri);
            System.out.println(url);
            System.out.println(uri);
            System.out.println(file);
        }catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


}
