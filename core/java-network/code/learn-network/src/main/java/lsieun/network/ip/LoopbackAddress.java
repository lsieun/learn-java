package lsieun.network.ip;

import java.net.InetAddress;

public class LoopbackAddress {
    public static void main(String[] args) {
        String host = InetAddress.getLoopbackAddress().getHostAddress();
        System.out.println(host);
    }
}
