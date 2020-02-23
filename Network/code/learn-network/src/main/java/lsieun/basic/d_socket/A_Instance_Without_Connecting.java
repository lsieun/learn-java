package lsieun.basic.d_socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class A_Instance_Without_Connecting {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            // fill in socket options
            SocketAddress address = new InetSocketAddress("time.nist.gov", 13);
            socket.connect(address);
            // work with the sockets...
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
