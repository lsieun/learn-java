package lsieun.network.hardware;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class DisplayInfo {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> interfaces;
            interfaces = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface ni : Collections.list(interfaces)) {
                displayNetworkInterfaceInformation(ni);
            }
        }
        catch (SocketException ex) {
            ex.printStackTrace();
        }
    }

    private static void displayNetworkInterfaceInformation(NetworkInterface ni) {
        try {
            System.out.println("Display name: " + ni.getDisplayName());
            System.out.println("Name: " + ni.getName());
            System.out.println("Supports Multicast: " + ni.supportsMulticast());
            Enumeration<InetAddress> addresses = ni.getInetAddresses();
            for (InetAddress addr : Collections.list(addresses)) {
                System.out.println("\tInetAddress: " + addr);
            }
            System.out.println();
        }
        catch (SocketException ex) {
            ex.printStackTrace();
        }
    }
}
