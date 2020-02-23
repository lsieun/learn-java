package lsieun.basic.d_socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class A_Instance_Local_Interface {
    public static void main(String[] args) {
        try {
            InetAddress inward = InetAddress.getByName("router");
            Socket socket = new Socket("mail", 25, inward, 0);
            // work with the sockets...
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
