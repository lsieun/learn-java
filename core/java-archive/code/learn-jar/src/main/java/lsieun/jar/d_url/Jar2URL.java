package lsieun.jar.d_url;

import java.net.MalformedURLException;
import java.net.URL;

public class Jar2URL {
    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test1() {
        // 第一种方式，是通过“jar:file:.../hello.jar!/.../Main.class”来构造URL
        try {
            URL url = new URL("jar:file:/home/liusen/workdir/git-repo/learn-java/core/java-util/code/learn-jar/target/hello.jar!/lsieun/start/Main.class");
            System.out.println(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        // 第二种方式，是通过"Class.getResource"来获取
        URL url = Jar2URL.class.getResource("/text/readme.txt");
        System.out.println(url);
    }
}
