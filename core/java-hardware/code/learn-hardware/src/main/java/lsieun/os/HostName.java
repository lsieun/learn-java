package lsieun.os;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostName {
    public static void main(String[] args) {

        String hostName = "unknownHost";
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            System.err.println("Could not resolve local host: " + e);
        }
        System.out.println(hostName);
    }
}
