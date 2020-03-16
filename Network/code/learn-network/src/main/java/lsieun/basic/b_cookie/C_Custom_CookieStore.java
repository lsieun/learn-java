package lsieun.basic.b_cookie;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;

public class C_Custom_CookieStore {

    static {
        CookieStore cookieStore = new PreferencePersistentCookieStore();
        CookieManager manager = new CookieManager(cookieStore, CookiePolicy.ACCEPT_ORIGINAL_SERVER);
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
