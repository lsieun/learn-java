package lsieun.basic.proxy;

public class B_HttpProxy_Squid {
    public static void main(String[] args) {
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "3128");

        String url = "https://httpbin.org/ip";
        HttpUtils.connect(url);
    }
}
