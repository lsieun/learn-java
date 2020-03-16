package lsieun.zzz;

import java.nio.charset.Charset;

public class TestCharset {
    public static void main(String[] args) {
        String csn = Charset.defaultCharset().name();
        System.out.println("Default Charset: " + csn);
    }
}
