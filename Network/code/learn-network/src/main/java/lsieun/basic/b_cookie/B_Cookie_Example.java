package lsieun.basic.b_cookie;

import java.net.CookieHandler;
import java.net.CookieManager;

public class B_Cookie_Example {

    static {
        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);
    }

    public static void main(String[] args) {
        HttpBinUtils.connect("http://httpbin.org/cookies/set/username/tom");
        System.out.println(CookieUtils.getCookieInfo2());

        HttpBinUtils.connect("http://httpbin.org/cookies/set/password/cat");
        System.out.println(CookieUtils.getCookieInfo2());

        HttpBinUtils.connect("http://httpbin.org/cookies");
        System.out.println(CookieUtils.getCookieInfo2());
    }




}
