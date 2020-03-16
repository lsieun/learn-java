package lsieun.basic.b_cookie;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

public class A_Enable_CookieManager {
    public static void main(String[] args) {
        CookieManager manager = new CookieManager();
        manager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
        CookieHandler.setDefault(manager);
    }
}
