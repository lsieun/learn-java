package lsieun.basic.proxy;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class B_HttpProxy_OneConnection {
    public static void main(String[] args) {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("http://proxy.memorynotfound.com", 80));

        String url = "https://httpbin.org/ip";
        HttpUtils.connect(url, proxy);
    }
}
