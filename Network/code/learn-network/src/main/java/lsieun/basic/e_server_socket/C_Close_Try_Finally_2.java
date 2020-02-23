package lsieun.basic.e_server_socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;

public class C_Close_Try_Finally_2 {
    public static final int port = 5000;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        try {
            SocketAddress address = new InetSocketAddress(port);
            server.bind(address);
            // ... work with the server socket
        } finally {
            try {
                server.close();
            } catch (IOException ex) {
                // ignore
            }
        }
    }
}
