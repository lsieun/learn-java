package lsieun.network.ip;

import java.net.InetSocketAddress;

/** The wildcard address to listen on all interfaces (either 0.0.0.0 or ::). */
public class WildCardAddress {
    public static void main(String[] args) {
        String address = new InetSocketAddress(0).getAddress().getHostAddress();
        System.out.println(address);
    }
}
