package lsieun.network.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DNS {
    public static void main(String[] args) throws UnknownHostException {
//        InetAddress address = InetAddress.getByName("www.oreilly.com");
//        System.out.println(address);
//        InetAddress address2 = InetAddress.getByName("184.30.181.37");
//        System.out.println(address2.getHostName());

//        try {
//            InetAddress[] addresses = InetAddress.getAllByName("www.oreilly.com");
//            for (InetAddress address : addresses) {
//                System.out.println(address);
//            }
//        } catch (UnknownHostException ex) {
//            System.out.println("Could not find www.oreilly.com");
//        }

        InetAddress me = InetAddress.getLocalHost();
        System.out.println(me);
    }
}
