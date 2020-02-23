package lsieun.basic.proxy;

public class C_SocketProxy_ConnectGoogle {
    public static void main(String[] args) {
        System.setProperty("socksProxyHost", "127.0.0.1");
        System.setProperty("socksProxyPort", "1080");
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        System.setProperty("jdk.tls.trustNameService", "true");
//        System.setProperty("javax.net.debug", "all");

//        String url = "https://httpbin.org/ip"; // error
//        String url = "https://httpbin.org"; // ok
//        String url = "https://www.bing.com"; // ok
        String url = "https://www.google.com"; // error
        HttpUtils.connect(url);
    }
}
