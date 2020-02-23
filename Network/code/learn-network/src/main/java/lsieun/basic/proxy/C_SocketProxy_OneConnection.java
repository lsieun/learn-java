package lsieun.basic.proxy;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class C_SocketProxy_OneConnection {
    public static void main(String[] args) {
        Proxy socksProxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 1080));

        String url = "https://httpbin.org/ip";
        HttpUtils.connect(url, socksProxy);
    }
}
