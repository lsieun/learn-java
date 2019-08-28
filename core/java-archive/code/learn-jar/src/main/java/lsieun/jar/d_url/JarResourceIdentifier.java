package lsieun.jar.d_url;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Scheme syntax:
 *    jar:<url>!/[<entry>]
 */
public class JarResourceIdentifier {
    public static void main(String[] args) {
        test("jar:file://localhost/home/liusen/workdir/git-repo/learn-java/core/java-util/code/learn-jar/target/hello.jar!/lsieun/start/Main.class");
        test("jar:http://www.abc.com/book/bookStore.jar!/home/everyday/Recommend.class");
    }

    public static void test(String str) {
        try {
            URL url = new URL(str);
            System.out.println(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
